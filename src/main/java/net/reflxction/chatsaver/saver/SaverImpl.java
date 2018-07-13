/*
 * * Copyright 2018 github.com/ReflxctionDev
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
package net.reflxction.chatsaver.saver;

/**
 * Implementation of {@link Saver}
 */
public class SaverImpl implements Saver {

    // The text in the text field, before switching worlds
    private String text;

    // Whether the saver was hasContent or not
    private boolean hasContent = true;

    /**
     * @return The text in the text field before switching worlds
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Set the text instance
     *
     * @param text Text to set
     */
    @Override
    public void setText(String text) {
        this.text = text;
        if (text.isEmpty()) {
            hasContent = true;
        } else {
            hasContent = false;
        }
    }

    /**
     * Clears the text
     */
    @Override
    public void clear() {
        setText("");
        this.hasContent = true;

    }

    /**
     * Whether the saver was hasContent or not
     *
     * @return True if the saver is cleaned
     */
    @Override
    public boolean hasContent() {
        return !hasContent;
    }

}
