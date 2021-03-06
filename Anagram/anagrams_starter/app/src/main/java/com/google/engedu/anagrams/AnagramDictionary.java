/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    public ArrayList<String> wordList;
    public HashSet<String> wordSet;
    public HashMap<String, ArrayList<String>> lettersToWord;

//    public
    private Random random = new Random();

    public AnagramDictionary(Reader reader) throws IOException {
        wordSet = new HashSet<String>();
        lettersToWord = new HashMap<String, ArrayList<String>>();
        wordList = new ArrayList<String>();
        BufferedReader in = new BufferedReader(reader);
        String line;

//        The following while loop will take words from the word.txt(The dictionary of words) and put it in a suitable data structure.

        while((line = in.readLine()) != null) {
            String word = line.trim();
            if(word.length() <= MAX_WORD_LENGTH && word.length() >=DEFAULT_WORD_LENGTH){

//        Here we are using a HASHSET. wordSet is a hashset contains all the words so as to avoid any sort of repeatition of
                wordList.add(word);

                if(!lettersToWord.containsKey(sortLetter(word))) {
                    lettersToWord.put(sortLetter(word), new ArrayList<String>());
                }
                lettersToWord.put(sortLetter(word), array(sortLetter(word),word));

            }
        }
    }

    public ArrayList<String> array(String key,String S){
        ArrayList<String> cc = new ArrayList<String>();
        cc = lettersToWord.get(key);
        cc.add(S);
        return cc;
    }

    public boolean isGoodWord(String word, String base) {

        if(validWord(word) && !(word.contains(base))){
            return true;
        }
        return false;
    }

    public boolean validWord(String word){
        for(String letter:wordList){
            if(letter.contentEquals(word)){
                return true;
            }
        }
        return false;
    }

//    The following function generates a list of anagrams that can be found in the given Dictionary for the Random Word given to the user.
    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        for(String letter:wordList){
            if(sortLetter(targetWord).contentEquals(sortLetter(letter))){
                result.add(letter);
            }
        }
        return result;
    }

    public String sortLetter(String S){
        char arr[] = new char[S.length()];
        for(int i = 0;i<S.length();i++){
            arr[i] = S.charAt(i);
        }

        for(int n = 0; n< S.length(); n++){
            for(int m = n+1; m<S.length(); m++){
                int k = arr[n];
                int l = arr[m];

                if(k>=l){
                    char c = arr[n];
                    arr[n] = arr[m];
                    arr[m] = c;
                }
            }
        }

        return String.valueOf(arr);

    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        result = lettersToWord.get(sortLetter(word));

        for(Map.Entry me : lettersToWord.entrySet()) {
            String S = sortLetter(me.getKey().toString());
            if(S.length() == (sortLetter(word).length()+1)){
                if(characterComparison(S, sortLetter(word))){
                    for(String m: lettersToWord.get(S)) {

                        char c = uniqueChar(S, sortLetter(word));
                        if(m.charAt(0) != c && m.charAt(S.length()-1) != c) {
                            result.add(m);
                        }
                    }
                }
            }
        }

        return result;
    }

    public char uniqueChar(String S, String word){
        char ch;
        int i=0,j = 0;

        while(i<word.length()) {
            if (word.charAt(i) != S.charAt(j)) {
                ch = S.charAt(j);
               return ch;
            }else{
                i += 1;
                j += 1;
            }
        }
        return ' ';
    }

    public boolean characterComparison(String sChar, String word){
        int flag = 0;
        int i=0,j = 0;

        while(i<word.length()){
            if(word.charAt(i) != sChar.charAt(j)){
                j = i+1;
                flag+=1;
                if(flag>1){
                    return false;
                }
            }else{
                i+=1;
                j+=1;

            }
        }
        return true;
    }

    public String pickGoodStarterWord() {
        int index = random.nextInt(wordList.size()-1);
        int minNumber = lettersToWord.get(sortLetter(wordList.get(index))).size();

        if(minNumber >= MIN_NUM_ANAGRAMS){
            return wordList.get(index);
        }else{
            return pickGoodStarterWord();
        }
    }
}

