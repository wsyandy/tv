/*
 * Copyright (c) 2013, Sam Malone. All rights reserved.
 * 
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 * 
 *  - Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  - Neither the name of Sam Malone nor the names of its contributors may be
 *    used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package tv.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ice
 */
public class Config {
        
    private String player;
    private String playerExecutable;
    private List<String> playerArguments;
    private List<String> sourceDirs;
    private String libraryName;
    private String mediainfoBinary;
    private String tvdbFile;
    
    public Config() {
        sourceDirs = new ArrayList<String>();
        playerArguments = new ArrayList<String>();
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public List<String> getSourceFolders() {
        return sourceDirs;
    }

    public void addSourceFolder(String sourceDir) {
        sourceDirs.add(sourceDir);
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getMediainfoBinary() {
        return mediainfoBinary;
    }

    public void setMediainfoBinary(String mediainfoBinary) {
        this.mediainfoBinary = mediainfoBinary;
    }

    public String getTVDBFile() {
        return tvdbFile;
    }

    public void setTVDBFile(String tvdbFile) {
        this.tvdbFile = tvdbFile;
    }

    public String getPlayerExecutable() {
        return playerExecutable;
    }

    public void setPlayerExecutable(String playerExecutable) {
        this.playerExecutable = playerExecutable;
    }

    public String[] getPlayerArguments() {
        return playerArguments.toArray(new String[] {});
    }

    public void addPlayerArgument(String playerArguments) {
        this.playerArguments.add(playerArguments);
    }
    
}
