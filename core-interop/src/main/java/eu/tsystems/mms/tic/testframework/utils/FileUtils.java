/*
 * (C) Copyright T-Systems Multimedia Solutions GmbH 2018, ..
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
 *
 * Contributors:
 *     Peter Lehmann <p.lehmann@t-systems.com>
 *     pele <p.lehmann@t-systems.com>
 */
package eu.tsystems.mms.tic.testframework.utils;

import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.AESEncrypter;
import de.idyl.winzipaes.impl.AESEncrypterJCA;
import eu.tsystems.mms.tic.testframework.exceptions.FileNotFoundException;
import eu.tsystems.mms.tic.testframework.exceptions.FennecSystemException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.Thread.currentThread;

/**
 * Created by pele on 24.11.2014.
 */
public final class FileUtils extends org.apache.commons.io.FileUtils {

    private static String lineBreak = "\n";

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    /**
     * Simply get the resource as a stream shortcut.
     *
     * @param fileInResources .
     * @return Input Stream.
     */
    public static InputStream getResourceInputStream(final String fileInResources) throws FileNotFoundException {
        InputStream resourceAsStream = currentThread().getContextClassLoader().getResourceAsStream(fileInResources);
        if (resourceAsStream == null) {
            throw new FileNotFoundException(fileInResources);
        }
        return resourceAsStream;
    }

    /**
     * Get an absolute file path from a resource file path.
     *
     * @param fileInResources .
     * @return Absolute file path.
     * @throws FileNotFoundException
     */
    public static String getAbsoluteFilePath(String fileInResources) throws FileNotFoundException {
        ClassLoader contextClassLoader = currentThread().getContextClassLoader();
        URL resource = contextClassLoader.getResource(fileInResources);
        if (resource == null) {
            throw new FileNotFoundException(fileInResources);
        }

        URI uri = null;
        try {
            uri = resource.toURI();
        } catch (URISyntaxException e) {
            throw new FennecSystemException("Error getting file uri: " + resource);
        }
        File file = new File(uri);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    /**
     * Read a resource file into a string.
     *
     * @param fileInResources .
     * @return string.
     * @throws IOException
     */
    public static String readFromResourceFile(String fileInResources) throws IOException {
        String absoluteFilePath = null;
        try {
            absoluteFilePath = getAbsoluteFilePath(fileInResources);
        } catch (FileNotFoundException e) {
            throw new FennecSystemException("Error loading file: " + fileInResources, e);
        }

        return readFromFile(absoluteFilePath);
    }

    /**
     * Read an absolute file into a string.
     *
     * @param absoluteFilePath .
     * @return String.
     * @throws IOException
     */
    public static String readFromFile(String absoluteFilePath) throws IOException {
        FileReader fileReader = new FileReader(absoluteFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            stringBuilder.append(line).append(lineBreak);

            // read a new line
            line = bufferedReader.readLine();
        }

        // reading is done
        bufferedReader.close();

        return stringBuilder.toString();
    }

    public static String getLineBreak() {
        return lineBreak;
    }

    public static void setLineBreak(String lineBreak) {
        FileUtils.lineBreak = lineBreak;
    }

    public static void zip(final File targetFile, final File... filesToAdd) throws ZipException {
        zip(targetFile, new ZipParameters(), filesToAdd);
    }

    public static void zip(final File targetFile, ZipParameters params, final File... filesToAdd) throws ZipException {
        if (params == null) {
            params = new ZipParameters();
        }
        if (filesToAdd == null) {
            throw new IllegalArgumentException("No files named to zip.");
        }
        final ZipFile zipFile = new ZipFile(targetFile);
        for (File file : filesToAdd) {
            zipFile.addFile(file, params);
        }
    }

    public static void zip(final File targetFile, final InputStream... inputStreams) throws ZipException {
        zip(targetFile, new ZipParameters(), inputStreams);
    }

    public static void zip(final File targetFile, ZipParameters params, final InputStream... inputStreams) throws ZipException {
        if (params == null) {
            params = new ZipParameters();
        }
        if (inputStreams == null) {
            throw new IllegalArgumentException("No files named to zip.");
        }
        final ZipFile zipFile = new ZipFile(targetFile);
        for (InputStream is: inputStreams) {
            zipFile.addStream(is, params);
        }
    }

    public static void zipWinZipAes256(final File targetFile, final String password, final File... filesToAdd) throws IOException {
        AESEncrypter aesEncrypter = new AESEncrypterJCA();
        aesEncrypter.init(password, 256);
        AesZipFileEncrypter zip = new AesZipFileEncrypter(targetFile.getAbsolutePath(), aesEncrypter);

        for (File file : filesToAdd) {
            final String name = file.getName();
            final String absolutePath = file.getAbsolutePath();
            zip.add(name, new FileInputStream(absolutePath), password);
        }
        zip.close();
    }

    /**
     * Unzip utility.
     *
     * @param infile               Name of zipped File
     * @param destinationDirectory Directoy to unzip
     */
    public static void unzip(final String infile, final String destinationDirectory) throws ZipException {
        unzip(infile, destinationDirectory, null);
    }

    /**
     * Unzip utility.
     *
     * @param infile               Name of zipped File
     * @param destinationDirectory Directoy to unzip
     * @param password             Password
     */
    public static void unzip(final String infile, final String destinationDirectory, final String password) throws ZipException {
        ZipFile zipFile = new ZipFile(infile);
        if (password != null) {
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
        }
        zipFile.extractAll(destinationDirectory);
    }

    public static URL getResourceURL(String resourceFile) {
        return currentThread().getContextClassLoader().getResource(resourceFile);
    }

    public static File getResourceFile(String resourceFile) {
        URL resourceURL = getResourceURL(resourceFile);
        return new File(resourceURL.getFile());
    }

    public static InputStream getLocalOrResourceFileAsStream(String resourceFile) {
        File localFile = new File(resourceFile);
        if (localFile.exists()) {
            try {
                return new FileInputStream(localFile);
            } catch (java.io.FileNotFoundException e) {
                LOGGER.error("Could not open local file: " + resourceFile);
            }
        }

        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceFile);
    }

}