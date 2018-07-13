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
 * Saver interface: Handles and saves all the required data, and passes it between classes through its implementations
 * Abiding by the GC, it doesn't use any static variables and instead uses OOP
 *
 * @see SaverImpl
 */
public interface Saver {

    /**
     * @return The latest text in the text field before switching worlds
     */
    String getText();

    /**
     * Set the text instance
     *
     * @param text Text to set
     */
    void setText(String text);

    /**
     * Clears the text
     */
    void clear();

    /**
     * Whether the saver was cleared or not
     *
     * @return True if the saver is cleaned
     */
    boolean isCleared();

}
