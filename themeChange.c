#include <stdio.h>
#include <curl/curl.h>
#include <curl/easy.h>
#include <string.h>

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

int main(void) {
    //Setup CURL and the local file
    CURL *curl;
    FILE *fp;
    char themeLocation[FILENAME_MAX];

    //Set the appropriate URL. This should be set by command line
    //The general idea is to download the appropriate text file that says
    //which theme to install.
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

        //Run appropriate .bat file
        //Maybe use a while loop to read characters until "Install" is seen
        //Then take everything from there until the '.', adding "Install" to the
        //string before adding those characters
        //This should allow changing into the install directory, and with
        //"AsLanguage3" or something added it should match the batch file.

        //Cleanup
        curl_easy_cleanup(curl);
        fclose(fp);
    }
    return 0;
}
