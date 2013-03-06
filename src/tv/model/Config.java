/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
