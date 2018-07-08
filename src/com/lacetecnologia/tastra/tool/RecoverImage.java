/*
 * Copyright (C) 2018 Leticia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.lacetecnologia.tastra.tool;

import com.lacetecnologia.tastra.Main;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Leticia
 */
public class RecoverImage {
     public static Image getImage(String path,String description){
         URL imageURL = Main.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
        
    }
}
