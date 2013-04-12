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
package tv.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import tv.ExitCode;
import tv.MediaInfo;
import tv.exception.InvalidArgumentException;
import tv.exception.ParseException;
import tv.model.Arguments;
import tv.model.Config;
import tv.model.PlayerInfo;

/**
 *
 * @author Sam Malone
 */
public class ConfigManager {
    
    /**
     * Merges the given Config with the given Arguments.
     * If config is null, the default config path will be checked
     * @param config Config
     * @param args Arguments
     * @throws InvalidArgumentException if windows 7 library name isn't valid
     */
    public static void mergeArguments(Config config, Arguments args) throws InvalidArgumentException, ParseException {
        if(config == null) {
            config = parseConfig(getDefaultConfigFile());
        }
        if(config.getLibraryName() != null) {
            if(LibraryManager.isWindows7()) {
                if(LibraryManager.isValidLibraryName(config.getLibraryName())) {
                    args.getSourceFolders().addAll(LibraryManager.parseLibraryFolders(config.getLibraryName()));
                } else {
                    throw new InvalidArgumentException("Windows 7 Library name is invalid", ExitCode.LIBRARY_NOT_FOUND);
                }
            }
        }
        if(config.getTVDBFile() != null) {
            TVDBManager.setTVDBFile(new File(config.getTVDBFile()));
        }
        if(config.getMediainfoBinary() != null) {
            MediaInfo.setExecutableFile(new File(config.getMediainfoBinary()));
        }
        PlayerInfo p = new PlayerInfo();
        if(args.getPlayerInfo().getPlayer() == null && config.getPlayer() != null) {
            p.setPlayer(config.getPlayer());
        } else {
            p.setPlayer(args.getPlayerInfo().getPlayer());
        }
        if(config.getPlayerExecutable() != null) {
            p.setPlayerExecutable(config.getPlayerExecutable());
        }
        if(config.getPlayerArguments() != null && config.getPlayerArguments().length > 0) {
            p.setPlayerArguments(config.getPlayerArguments());
        }
        args.setPlayerInfo(p);
        if(config.getSourceFolders() != null) {
            args.getSourceFolders().addAll(config.getSourceFolders());
        }
    }
    
    /**
     * Get the default config file
     * @return Config File
     */
    public static File getDefaultConfigFile() {
        if(LibraryManager.isWindows()) {
            return new File("C:\\ProgramData\\" + System.getProperty("user.name") + "\\tv\\tv.conf");
        } else {
            return new File(System.getProperty("user.home") + "/.tv/tv.conf");
        }
    }
    
    /**
     * Parses the configFile and returns a Config object
     * @param configFile Config File
     * @return Config
     */
    public static Config parseConfig(File configFile) throws ParseException {
        Config c = new Config();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), "UTF8"));
            String line;
            boolean firstLine = true;
            while((line = br.readLine()) != null) {
                if(firstLine) {
                    if(line.startsWith("\uFEFF")) { // remove UTF8 BOM
                        line = line.substring(1);
                    }
                    firstLine = false;
                }
                parseLine(c, line);
            }
            br.close();
        } catch(IOException e) {
            
        } catch(ParseException e) {
            throw e;
        }
        return c;
    }
    
    /**
     * Parses a line of the config file and adds the data to the given
     * Config object
     * @param c Config
     * @param line Line of Config File
     */
    private static void parseLine(Config c, String line) throws ParseException {
        if(line.isEmpty() || line.charAt(0) == '#') {
            return;
        }
        String key, value;
        try {
            int equalsIndex = line.indexOf('=');
            key = line.substring(0, equalsIndex).trim();
            value = line.substring(equalsIndex + 1).trim();
        } catch(IndexOutOfBoundsException ex) {
            throw new ParseException("Unable to parse the line " + line, ExitCode.CONFIG_PARSE_ERROR);
        }
        if(value.isEmpty()) {
            value = null;
        }
        if(key.equals("TVDB_FILE")) {
            c.setTVDBFile(value);
        }
        if(key.equals("SOURCE")) {
            c.addSourceFolder(value);
        }
        if(key.equals("MEDIAINFO_BINARY")) {
            c.setMediainfoBinary(value);
        }
        if(key.equals("LIBRARY_NAME")) {
            c.setLibraryName(value);
        }
        if(key.equals("PLAYER")) {
            c.setPlayer(value);
        }
        if(key.equals("PLAYER_EXECUTABLE")) {
            c.setPlayerExecutable(value);
        }
        if(key.equals("PLAYER_ARGUMENTS")) {
            c.addPlayerArgument(value);
        }
    }
    
}