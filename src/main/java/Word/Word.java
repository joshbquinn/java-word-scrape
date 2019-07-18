package Word;

import java.util.List;

public abstract class Word {

    List<String> words;

    public Word(){
        words = null;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public void appendWords(List<String> words) {
        this.words.addAll(words);
    }
}
