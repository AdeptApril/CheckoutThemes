#include <stdio.h>
#include <curl/curl.h>
#include <curl/easy.h>
#include <string.h>
#include <unistd.h> //For chdir()
#include <ctype.h> //For tolower() and toupper()
#define MAX_LIBRARY_NAME 20

struct FtpFile {
  const char *filename;
  FILE *stream;
};

size_t write_data(void *ptr, size_t size, size_t nmemb, FILE *stream);
static size_t my_fwrite(void *buffer, size_t size, size_t nmemb, void *stream);
int runExec(char * filename, char * params);
void setInstallName(char * installName, char * themeLocation);
void runBatch(char * installName, char * libraryName, short languageNum);

int main(int argc, char *argv[]) {
    if ((argc != 4) || (*argv[2] < 1) || (*argv[3] < 1) ) {
        printf("Usage: ThemeUpdater (libraryName) (machineNumber) (languageNumber)\n");
        exit(1);
    }
    //Setup CURL and the local file
    CURL *curl;
    FILE *fp;
    char themeLocation[FILENAME_MAX];
    char libraryName[MAX_LIBRARY_NAME];
    int machineNum;
    int languageNum;
    char installName[FILENAME_MAX];
    char params[FILENAME_MAX];

    //Read in the command-line variables
    strcpy(libraryName, argv[1]);
    machineNum = *argv[2];
    languageNum = *argv[3];

    //Set the appropriate URL. This should be set by command line
    //The general idea is to download the appropriate text file that says
    //which theme to install.
    //The name of the library should also be set with the command line.
    //The language # should also be set.
    //And the self-check # should be set.
    //strcpy(libraryName, "Sequoya");
    //languageNum = 3;
    char url[FILENAME_MAX]; //Set by variables, now. = "http://www.checkoutthemes.com/sequoya/checkout3/lang3choice.txt";
    char outfilename[FILENAME_MAX] = "themechoice.txt";

    //Setup URL
    //Setup library name for URL. Needs to be all lowercase. The first
    //letter should be capitalized in later usage.
    int i;
    for(i = 0; libraryName[i]; i++) {
        libraryName[i] = tolower(libraryName[i]);
    }
    sprintf(url, "http://www.checkoutthemes.com/%s/checkout%d/lang%dchoice.txt", libraryName, machineNum, languageNum);
    libraryName[0] = toupper(libraryName[0]); //First letter is capitalized outside of URL usage

    printf("url: %s\n", url);
    exit(0);

    curl = curl_easy_init();
    if (curl) {
        //CURLcode res;
        fp = fopen(outfilename,"wb");
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_data);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, fp);
        //res =
        curl_easy_perform(curl);
        //Cleanup
        //curl_easy_cleanup(curl);
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
                themeLocation[pos] = (char)c;
                pos++;
            }
            if(pos >= (FILENAME_MAX - 1) ) {
                //Output error
                exit(1);
            }
        } while(c != EOF && c!= '\n');
    }

    printf("File to download: %s\n", themeLocation); //Debugging.

    //Download the file specified in the txt file.
//#if(0) //Used to decrease the amount I'm downloading while testing
    curl = curl_easy_init();
    if (curl) {
        struct FtpFile ftpfile = {
            "theme.exe",
            NULL
        };
        strcpy(url, themeLocation);
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, my_fwrite);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &ftpfile);
        //res =
        curl_easy_perform(curl);
        /* if(CURLE_OK != res) {
            // we failed
            fprintf(stderr, "curl told us %d\n", res);
        } */

        if(ftpfile.stream){
            fclose(ftpfile.stream);
        }

        //Decompress file
        runExec((char *)ftpfile.filename, NULL);
//#endif
        //Run appropriate .bat file
        //This should allow changing into the install directory, and with
        //"AsLanguage3" or something added it should match the batch file.
        setInstallName((char*)&installName, (char*)&themeLocation); //This is to get the directory to cd into
        runBatch((char*)&installName, (char*)&libraryName, languageNum);

        //Cleanup
        //Remember to clean up theme.exe, themechoice.txt, and whatever the install directory was.

        curl_easy_cleanup(curl);
        fclose(fp);

        //Deleting of the created files
        //This would be much better done with something other than system(),
        //but when trying to use runExec, no DOS commands would work. They
        //seem to require the path to be explicit. This would be fine if I
        //could find some way to get the path from Windows that works in
        //both WinXP and Windows 7. So far as I can tell, the way to do it
        //changed multiple times between those versions, and is not possible.
        //However, system() still works. It's just not a good idea.

        //Delete the install directory
        sprintf(params, "rmdir %s /s /q", (char*)installName);
        system(params);

        //Delete theme.exe
        sprintf(params, "del theme.exe /q");
        system(params);

        //Delete themechoice.txt
        sprintf(params, "del themechoice.txt /q");
        system(params);

    } //Needs to be commented out with the #endif
    return 0;
} //End of main()

int runExec(char * filename, char * params){
/*
    //Taken from:
    //http://faq.cprogramming.com/cgi-bin/smartfaq.cgi?answer=1044654269&id=1043284392
      HINSTANCE hRet = ShellExecute(
        HWND_DESKTOP, //Parent window
        "open",       //Operation to perform
        filename,       //Path to program
        params,         //Parameters
        NULL,         //Default directory
        SW_SHOW);     //How to open

  //The function returns a HINSTANCE (not really useful in this case)
  //So therefore, to test its result, we cast it to a LONG.
  //Any value over 32 represents success!

  if((LONG)hRet <= 32)
  {
    MessageBox(HWND_DESKTOP,"Unable to start program","",MB_OK);
    return 1;
  }
*/
    //char cmdLine[FILENAME_MAX];
    //sprintf(cmdLine, "%s %s", filename, params);
    //printf("cmdLine: %s\n", cmdLine);
    //Taken from https://msdn.microsoft.com/en-us/library/ms682512(VS.85).aspx
    STARTUPINFO si;
    PROCESS_INFORMATION pi;

    ZeroMemory( &si, sizeof(si) );
    si.cb = sizeof(si);
    ZeroMemory( &pi, sizeof(pi) );

    printf("filename: %s\n", filename);
    system("cd");
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
{
  struct FtpFile *out=(struct FtpFile *)stream;
  if(out && !out->stream) {
    /* open file for writing */
    out->stream=fopen(out->filename, "wb");
    if(!out->stream)
      return -1; /* failure, can't open file to write */
  }
  return fwrite(buffer, size, nmemb, out->stream);
}

void setInstallName(char * installName, char * themeLocation) {
    //Maybe use a loop to read characters until "Install" is seen
    //Then take everything from there until the '.', adding "Install" to the
    //string before adding those characters
    int i;
    strcpy(installName, "Install");
    printf("installName before: %s\n", installName);
    //printf("themeLocation[0] %c\n", themeLocation[0]);
    if(themeLocation) { //Not sure if it's strictly necessary to check this.
        for(i=0; i<strlen(themeLocation); i++) {
            //printf("%c", themeLocation[i]);
            if ( (themeLocation[i] == EOF) || (themeLocation[i] == '\n') )
                break;
            if (themeLocation[i] != 'I')
                continue;
            //Massive if statement to see if it's "Install"
            if ( (themeLocation[i+1] == 'n') && (themeLocation[i+2] == 's') && (themeLocation[i+3] == 't') && (themeLocation[i+4] == 'a') && (themeLocation[i+5] == 'l') && (themeLocation[i+6] == 'l') ) {
                //printf("\nthemeLocation[i] %c\n", themeLocation[i]);
                int j;
                j = 7;
                //printf("themeLocation[i+j] (before while): %c\n", themeLocation[i+j]);
                //While not ".exe"
                while ( !( (themeLocation[i+j] == '.') && (themeLocation[i+j+1] == 'e') && (themeLocation[i+j+2] == 'x') && (themeLocation[i+j+3] == 'e') ) ){
                    //printf("themeLocation[i+j]: %c\n", themeLocation[i+j]);
                    //printf("themeLocation[.exe] area: %c%c%c%c\n", themeLocation[i+j], themeLocation[i+j+1], themeLocation[i+j+2], themeLocation[i+j+3] );
                    if ( (themeLocation[i+j] == '\n') || (themeLocation[i+j] == EOF) )
                        exit(1); //This shouldn't happen.
                    installName[j] = themeLocation[i+j];
                    j++;
                    installName[j] = '\0';
                    if (j>100) //failsafe on the while loop
                        break;
                }
            } else { //Should be reached when it's 'I' but not "Install"
                continue;
            }
        }
    }
    printf("installName after: %s\n", installName);
    return;
}

void runBatch(char * installName, char * libraryName, short languageNum){
    chdir(installName); //Get into the directory to run the batch file first.
    //Using sprintf, then a while loop, then sprintf again, batchName should
    //eventually end up being in the format I've been creating batchNames
    char batchName[FILENAME_MAX];
    sprintf(batchName, "%s%d", libraryName, languageNum);
    int b = strlen(batchName);
    int i = 0;
    //While not "Theme"
    while ( !( (installName[i] == 'T') && (installName[i+1] == 'h') && (installName[i+2] == 'e') && (installName[i+3] == 'm') && (installName[i+4] == 'e')) ){
        //printf("installName[i+j]: %c\n", installName[i+j]);
        //printf("installName[.exe] area: %c%c%c%c\n", installName[i+j], installName[i+j+1], installName[i+j+2], installName[i+j+3] );
        if ( (installName[i] == '\n') || (installName[i] == EOF) )
            exit(1); //This shouldn't happen.
        batchName[b+i] = installName[i];
        i++;
    }
    //strcat() would be another option, here, rather than giving a mid-string address for batchName
    sprintf(&batchName[b+i], "AsLanguage%d.bat", languageNum);
    printf("batchName: %s\n", batchName);

    //Now to actually execute the batch file
    runExec(batchName, NULL);
    chdir(".."); //Get back to the original directory.
    return;
}
