#include <stdio.h>
#include <curl/curl.h>
#include <curl/easy.h>
#include <string.h>
#define MAX_LIBRARY_NAME 20

size_t write_data(void *ptr, size_t size, size_t nmemb, FILE *stream) {
    size_t written;
    written = fwrite(ptr, size, nmemb, stream);
    return written;
}

struct FtpFile {
  const char *filename;
  FILE *stream;
};

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

int extractTheme(const char * filename){
    //Taken from:
    //http://faq.cprogramming.com/cgi-bin/smartfaq.cgi?answer=1044654269&id=1043284392
      HINSTANCE hRet = ShellExecute(
        HWND_DESKTOP, //Parent window
        "open",       //Operation to perform
        filename,       //Path to program
        NULL,         //Parameters
        NULL,         //Default directory
        SW_SHOW);     //How to open

  /*
  The function returns a HINSTANCE (not really useful in this case)
  So therefore, to test its result, we cast it to a LONG.
  Any value over 32 represents success!
  */

  if((LONG)hRet <= 32)
  {
    MessageBox(HWND_DESKTOP,"Unable to start program","",MB_OK);
    return 1;
  }

  return 0;
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
                    //if (j>50) //failsafe on the while loop
                    //    break;
                }
            } else { //Should be reached when it's 'I' but not "Install"
                continue;
            }
        }
    }
    printf("installName after: %s", installName);
    return;
}

int main(void) {
    //Setup CURL and the local file
    CURL *curl;
    FILE *fp;
    char themeLocation[FILENAME_MAX];
    char libraryName[MAX_LIBRARY_NAME];
    short languageNum;
    char installName[FILENAME_MAX];

    //Set the appropriate URL. This should be set by command line
    //The general idea is to download the appropriate text file that says
    //which theme to install.
    //The name of the library should also be set with the command line.
    //The language # should also be set.
    //And the self-check # should be set.
    strcpy(libraryName, "Sequoya");
    languageNum = 3;
    char *url = "http://www.checkoutthemes.com/sequoya/checkout3/lang3choice.txt";
    char outfilename[FILENAME_MAX] = "themechoice.txt";
    curl = curl_easy_init();
    if (curl) {
        CURLcode res;
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
//if(0) //Used to decrease the amount I'm downloading while testing
    curl = curl_easy_init();
    if (curl) {
        struct FtpFile ftpfile = {
            "theme.exe",
            NULL
        };
        url = themeLocation;
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
        extractTheme(ftpfile.filename);
//#endif
        //Run appropriate .bat file
        //This should allow changing into the install directory, and with
        //"AsLanguage3" or something added it should match the batch file.
        setInstallName(&installName, &themeLocation);

        //Cleanup
        curl_easy_cleanup(curl);
        fclose(fp);
    } //Needs to be commented out with the #endif
    return 0;
}
