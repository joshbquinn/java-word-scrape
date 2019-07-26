# Word Scraper 

The program scrapes words from a URL, identifies keywords by their repetition and stores them in .txt files.

- Creates a string of all webpage content specified by a URL.
- Cleans up the webpage string to remove unwanted markup/javascript and leave only the text.
- Creates a webpage word list of this string.  

- Creates list of exclusion words to cross check the webpage words list against. 
- Exclusion words removed from the page words list.

- Uses the webwords list to create a map in the form of <word:frequency>
- Create a LinkedMap in descending order 

- Creates a unique file (url name + datetime stamp) to store keywords.

# Instructions 

Pipeline script from SCM: to configure the job in Jenkins Pipeline add the following git repo with default master branch:  
https://github.com/joshbquinn/word_scraper.git

To run from command line:
- run .jar file from command line with URL argument enclosed in double quotes. 
- If a URL is not specified a default URL string will run. 


# Future Improvements 

- Handle certain exceptions 
    - Server returned HTTP response code: 403 for URL
    - java.net.MalformedURLException: no protocol


# Test URL with .jar
 
- https://www.independent.ie/sport/golf/the-british-open/my-da-would-have-borrowed-money-to-buy-me-golf-clubs-shane-lowry-opens-up-on-his-familys-influence-38348921.html
