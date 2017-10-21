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

package com.google.engedu.wordstack;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.EmptyStackException;
import java.util.Stack;

public class StackedLayout extends LinearLayout {

    private Stack<View> tiles = new Stack();

    public StackedLayout(Context context) {
        super(context);
    }

//    CHANGES MADE
    public void push(View tile) {
        if(!empty()) {
            removeView(tiles.peek());
        }

        tiles.push(tile);
        addView(tile);

    }
// CHANGES MADE
    public View pop() throws EmptyStackException {
        try{
        View popped = null;
        if(!empty()){
            popped = tiles.pop();
            removeView(popped);
            addView(tiles.peek());
            return popped;
        }else{
            return popped;
        }
        }catch (EmptyStackException e){
            return null;
        }
    }

    public View peek() {
        return tiles.peek();
    }

    public boolean empty() {
        return tiles.empty();
    }
//    CHANGES MADE
    public void clear() {
        while(!tiles.empty()){
            tiles.pop();
        }
    }
}
