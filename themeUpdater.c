#include <stdio.h>
#include <curl/curl.h>
#include <curl/easy.h>
#include <string.h>
#include <unistd.h> //For chdir()
#include <ctype.h> //For tolower() and toupper()
#define MAX_LIBRARY_NAME 20
typedef enum { false, true } bool; //Being able to use bool is nice.

struct FtpFile {
  const char *filename;
  FILE *stream;
};

size_t write_data(void *ptr, size_t size, size_t nmemb, FILE *stream);
    //Used in first cURL call (getting a text file)
static size_t my_fwrite(void *buffer, size_t size, size_t nmemb, void *stream);
    //Used in second cURL call (downloading a self-executing compressed file
int run_exec(char * filename, char * params);
    //Used in a couple spots -- for running the compressed file, or a batch file.
void set_install_name(char * install_name, char * theme_location);
    //"install_name" becomes the directory that the program goes into after extracting
bool ok_install_name(char * install_name);
    //Check to make sure that the install name doesn't contain bad characters.
void run_batch(char * install_name, char * library_name, short language_num);
    //Goes into the install_name directory, runs the batch file, backs out.

int main(int argc, char *argv[]) {
    //Make sure that language number and machine number are between 1 and 9.
    if ( (argc != 4) || (*argv[2] < '1') || (*argv[2] > '9')
        || (*argv[3] < '1') || (*argv[3] > '9') ) {
        printf("Usage: ThemeUpdater (Library Name) (Machine Number(1 to 9)) (Language Number(1 to 9))\n");
        exit(1);
    }
    //Make sure the library name will not cause an out-of-bounds memory problem.
    if ( strlen(argv[1]) >= MAX_LIBRARY_NAME ) {
        printf("Library name too long. Please contact program author if this was in error.");
        exit(2);
    }
    printf("Downloading and installing a theme from checkoutthemes.com.\n");
    //Setup CURL and the local file
    CURL *curl;
    FILE *fp;
    char theme_location[FILENAME_MAX];
    char library_name[MAX_LIBRARY_NAME];
    int machine_num;
    int language_num;
    char install_name[FILENAME_MAX];
    char params[FILENAME_MAX];

    //Read in the command-line variables
    strcpy(library_name, argv[1]);
    machine_num = *argv[2] - '0';
    language_num = *argv[3] - '0';

    //TODO
    //Check the library name against possible libraries

    //Set the appropriate URL. This should be set by command line
    //The general idea is to download the appropriate text file that says
    //which theme to install.
    //The name of the library should also be set with the command line.
    //The language # should also be set.
    //And the self-check # should be set.

    char url[FILENAME_MAX];
    char outfilename[FILENAME_MAX] = "themechoice.txt";

    //Setup URL
    //Setup library name for URL. Needs to be all lowercase. The first
    //letter should be capitalized in later usage.
    int i;
    for(i = 0; library_name[i]; i++) {
        library_name[i] = tolower(library_name[i]);
    }
    sprintf(url, "http://www.checkoutthemes.com/%s/checkout%d/lang%dchoice.txt", library_name, machine_num, language_num);
    library_name[0] = toupper(library_name[0]); //First letter is capitalized outside of URL usage

    printf("Theme choice URL: %s\n", url);

    curl = curl_easy_init();
    if (curl) {
        printf("Downloading that text file.\n");
        CURLcode res;
        fp = fopen(outfilename,"wb");
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_data);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, fp);
        res = curl_easy_perform(curl);
        if(CURLE_OK != res) {
            // we failed
            fprintf(stderr, "curl told us %d\n", res);
            exit(3);
        }
        //Cleanup
        fclose(fp);
        curl_easy_cleanup(curl);
    }

    //Determine the file specified in the downloaded file.
    fp = fopen(outfilename,"r");
    int pos = 0;
    int c;
    if(fp) { //Read the first line of text from the file.
        do{
            c = fgetc(fp);
            if(c != EOF) {
                theme_location[pos] = (char)c;
                pos++;
            }
            if(pos >= (FILENAME_MAX - 1) ) {
                //Output error
                exit(4);
            }
        } while(c != EOF && c!= '\n');
    }

    printf("File to download: %s\n", theme_location); //Debugging. But kept because it's seems useful to mention.

    //TODO:
    //Check that theme_location is an acceptable file

    //Download the file specified in the txt file.
    curl = curl_easy_init();
    if (curl) {
        CURLcode res;
        struct FtpFile ftpfile = {
            "theme.exe",
            NULL
        };
        strcpy(url, theme_location);
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, my_fwrite);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &ftpfile);
        /* Switch on full protocol/debug output */
        curl_easy_setopt(curl, CURLOPT_VERBOSE, 1L);

        res = curl_easy_perform(curl);

        curl_easy_cleanup(curl);

        if(CURLE_OK != res) {
            // we failed
            fprintf(stderr, "curl told us %d\n", res);
            exit(5);
        }

        if(ftpfile.stream){
            fclose(ftpfile.stream);
        }
        printf("File downloaded.\n");

        //Decompress file
        printf("Decompressing file.\n");
        run_exec("theme.exe /s", NULL);
        printf("Completed decompression.\n");

        //Run appropriate .bat file
        //This should allow changing into the install directory, and with
        //"AsLanguage3" or something added it should match the batch file.
        set_install_name((char*)&install_name, (char*)&theme_location); //This is to get the directory to cd into
        if( !ok_install_name((char*)&install_name) ) {
            //Check the install name for bad characters, and exit if found.
            printf("Something went wrong with the install name. Exiting\n");
            exit(6);
        }
        printf("Running install batch file\n");
        run_batch((char*)&install_name, (char*)&library_name, language_num);
        printf("Theme installed\n");

        //Cleanup
        //Remember to clean up theme.exe, themechoice.txt, and whatever the install directory was.

        printf("Cleaning up, deleting downloaded/created files.\n");
        curl_global_cleanup();
        fclose(fp);

        //Deleting of the created files
        //This would be much better done with something other than system(),
        //but when trying to use run_exec, no DOS commands would work. They
        //seem to require the path to be explicit. This would be fine if I
        //could find some way to get the path from Windows that works in
        //both WinXP and Windows 7. So far as I can tell, the way to do it
        //changed multiple times between those versions, and is not possible.
        //However, system() still works. It's just not a good idea.

        //Delete the install directory
        sprintf(params, "rmdir %s /s /q", (char*)install_name);
        system(params);

        //Delete theme.exe
        sprintf(params, "del theme.exe /q");
        system(params);

        //Delete themechoice.txt
        sprintf(params, "del themechoice.txt /q");
        system(params);
    }
    printf("Program finished.\n");
    return 0;
} //End of main()

int run_exec(char * filename, char * params){
    //Taken from https://msdn.microsoft.com/en-us/library/ms682512(VS.85).aspx
    STARTUPINFO si;
    PROCESS_INFORMATION pi;

    ZeroMemory( &si, sizeof(si) );
    si.cb = sizeof(si);
    ZeroMemory( &pi, sizeof(pi) );

    //printf("filename: %s\n", filename); //This and the next line left for debugging.
    //system("cd");
    // Start the child process.
    if( !CreateProcess( NULL,   // No module name (use command line)
        filename,        // Command line
        NULL,           // Process handle not inheritable
        NULL,           // Thread handle not inheritable
        FALSE,          // Set handle inheritance to FALSE
        0,              // No creation flags
        NULL,           // Use parent's environment block
        NULL,           // Use parent's starting directory
        &si,            // Pointer to STARTUPINFO structure
        &pi )           // Pointer to PROCESS_INFORMATION structure
    )
    {
        printf( "CreateProcess failed (%d).\n", (int)GetLastError() );
        return 1;
    }

    // Wait until child process exits.
    WaitForSingleObject( pi.hProcess, INFINITE );

    // Close process and thread handles.
    CloseHandle( pi.hProcess );
    CloseHandle( pi.hThread );

  return 0;
}

size_t write_data(void *ptr, size_t size, size_t nmemb, FILE *stream) {
    size_t written;
    written = fwrite(ptr, size, nmemb, stream);
    return written;
}

static size_t my_fwrite(void *buffer, size_t size, size_t nmemb, void *stream)
{ //verbatim from http://curl.haxx.se/libcurl/c/ftpget.html
  struct FtpFile *out=(struct FtpFile *)stream;
  if(out && !out->stream) {
    /* open file for writing */
    out->stream=fopen(out->filename, "wb");
    if(!out->stream)
      return -1; /* failure, can't open file to write */
  }
  return fwrite(buffer, size, nmemb, out->stream);
}

void set_install_name(char * install_name, char * theme_location) {
    //Use a loop to read characters until "Install" is seen
    //Then take everything from there until the '.exe', adding "Install" to the
    //string before adding those characters
    int i;
    strcpy(install_name, "Install");
    if(theme_location) { //Not sure if it's strictly necessary to check this.
        for(i=0; i<strlen(theme_location); i++) {
            if ( (theme_location[i] == EOF) || (theme_location[i] == '\n') )
                break;
            if (theme_location[i] != 'I')
                continue;
            //Massive if statement to see if it's "Install"
            //TODO:
            //Use strncmp(). See http://en.cppreference.com/w/c/string/byte/strncmp
            if ( (theme_location[i+1] == 'n') && (theme_location[i+2] == 's') && (theme_location[i+3] == 't') && (theme_location[i+4] == 'a') && (theme_location[i+5] == 'l') && (theme_location[i+6] == 'l') ) {
                int j;
                j = 7;
                //While not ".exe"
                while ( !( (theme_location[i+j] == '.') && (theme_location[i+j+1] == 'e') && (theme_location[i+j+2] == 'x') && (theme_location[i+j+3] == 'e') ) ){
                    if ( (theme_location[i+j] == '\n') || (theme_location[i+j] == EOF) )
                        exit(7); //This shouldn't happen.
                    install_name[j] = theme_location[i+j];
                    j++;
                    install_name[j] = '\0'; //If the end of the string isn't NULL, it can cause problems.
                    if (j>100) //failsafe on the while loop
                        break;
                }
            } else { //Should be reached when it's 'I' but not "Install"
                continue;
            }
        }
    }
    return;
}

bool ok_install_name(char * install_name) {
    //return true if no checked problematic characters appear
    //return false if one of those characters appear
    int length;
    length = strlen(install_name);

    if ( strstr(install_name, "\\") || strstr(install_name, "/") ) {
        return false;
    }

    //install_name should definitely have "Install" and ".bat" in it, so
    //it's at least 11 characters long. Probably can assume longer due to
    //other things in a theme name.
    if ( length < 12 || install_name == NULL ) {
        return false;
    }

    //These are all batch files. Anything else should cause an exit.
    if ( install_name[length-4] != '.' &&
            install_name[length-3] != 'b' &&
            install_name[length-2] != 'a' &&
            install_name[length-1] != 't' ) {
        return false;
    }
    return true;
}

void run_batch(char * install_name, char * library_name, short language_num){
    chdir(install_name); //Get into the directory to run the batch file first.
    //Using sprintf, then a while loop, then sprintf again, batchName should
    //eventually end up being in the format I've been creating batchNames
    char batchName[FILENAME_MAX];
    sprintf(batchName, "%s%d", library_name, language_num);
    int b = strlen(batchName);
    int i = 0;
    //While not "Theme"
    //TODO:
    //Use strncmp(). See http://en.cppreference.com/w/c/string/byte/strncmp
    while ( !( (install_name[i] == 'T') && (install_name[i+1] == 'h') && (install_name[i+2] == 'e') && (install_name[i+3] == 'm') && (install_name[i+4] == 'e')) ){
        if ( (install_name[i] == '\n') || (install_name[i] == EOF) )
            exit(8); //This shouldn't happen.
        batchName[b+i] = install_name[i];
        i++;
    }
    //strcat() would be another option, here, rather than giving a mid-string address for batchName
    sprintf(&batchName[b+i], "AsLanguage%d.bat", language_num);
    //printf("batchName: %s\n", batchName);

    //Now to actually execute the batch file
    system("@echo OFF");
    run_exec(batchName, NULL);
    system("@echo ON");
    chdir(".."); //Get back to the original directory.
    return;
}
