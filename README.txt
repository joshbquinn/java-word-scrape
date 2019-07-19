FileManager
- pathExists()
- test file is moved
- test file location exists
- test IOException's for read and write file methods
- test directory is created

WordFetcher
- test url is not empty
- test wordFetch returns a String

WordManager
- test Lists are not empty
- test stringToList returns a string
- test words greater than 4 are not removed from removeFourLetterWords()
- assert that inclusion words are not removed

PageContentCleaner
- assert a string is returned from removeUnwantedHTML()
- assert a string is returned from removeIllegalCharacters()
- assert a string is returned from keepParagraphContent()
- assert a string is returned from keepHTagContent()


WordScraperApplication()
- create text files
- assert path exists
- assert webContent String created
- assert string does not contain certain characters
- assert scraped List not null
- assert exclusions/inclusions List not null
- assert scraped list has changed (different memory reference?)
- assert that illegal characters have been removed.




Behaviour tests
- Test whether methods were called with correct parameters

State tests
- Tests the result i.e. validates it