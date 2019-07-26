# Word Scraper 

**Outline**

The application scrapes words from a webpage, identifies keywords by their repetition and stores them in a .txt file.

**Program steps**
- Create a string of all webpage content specified by a URL.
- Remove unwanted markup/javascript and leave only the text.
- Create a list (webWords) of the remaining words.  

- Remove words < four letters from webWords.
- Create a list of exclusionWords specified by URL.  
- Remove exclusionWords from webWords.

- Check the frequency of each word in webWords using Map<word : frequency>  
- Create a LinkedMap<word : frequency> with values in descending order called keyWords. 
- Create a unique file (urlString + datetime stamp) & write keyWords.


**Instructions** 

To run from command line:
- run .jar file with URL argument enclosed in double quotes. 

**Improvements**
- Handle certain exceptions when running jar file without correct argument
    - Server returned HTTP response code: 403 for URL
    - java.net.MalformedURLException: no protocol


**Test URL with .jar**
java -jar "https://www.independent.ie/sport/golf/the-british-open/my-da-would-have-borrowed-money-to-buy-me-golf-clubs-shane-lowry-opens-up-on-his-familys-influence-38348921.html"
