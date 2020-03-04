/*
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
 *     Peter Lehmann
 *     pele
 */
package eu.tsystems.mms.tic.testframework.report.model.context.report;

import eu.tsystems.mms.tic.testframework.common.PropertyManager;
import eu.tsystems.mms.tic.testframework.constants.TesterraProperties;
import eu.tsystems.mms.tic.testframework.exceptions.TesterraRuntimeException;
import eu.tsystems.mms.tic.testframework.report.model.context.Screenshot;
import eu.tsystems.mms.tic.testframework.report.model.context.Video;
import eu.tsystems.mms.tic.testframework.utils.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Report {

    private static final Logger LOGGER = LoggerFactory.getLogger(Report.class);

    private static final String DEFAULT_REPORTDIR = "testerra-report";
    public static final File REPORT_DIRECTORY;

    public static final String FRAMES_FOLDER_NAME = "frames";
    public static final String METHODS_FOLDER_NAME = "methods";
    public static final String SCREENSHOTS_FOLDER_NAME = "screenshots";
    public static final String VIDEO_FOLDER_NAME = "videos";
    public static final String XML_FOLDER_NAME = "xml";

    static {
        /*
        Initialize report directory
         */
        FileUtils fileUtils = new FileUtils();
        REPORT_DIRECTORY = fileUtils.createTempDir(DEFAULT_REPORTDIR);
        LOGGER.info("Preparing report in " + Report.REPORT_DIRECTORY.getAbsolutePath());
    }

    public static final File FRAMES_DIRECTORY = new File(REPORT_DIRECTORY, FRAMES_FOLDER_NAME);
    public static final File METHODS_DIRECTORY = new File(FRAMES_DIRECTORY, METHODS_FOLDER_NAME);
    public static final File SCREENSHOTS_DIRECTORY = new File(REPORT_DIRECTORY, SCREENSHOTS_FOLDER_NAME);
    public static final File VIDEO_DIRECTORY = new File(REPORT_DIRECTORY, VIDEO_FOLDER_NAME);
    public static final File XML_DIRECTORY = new File(REPORT_DIRECTORY, XML_FOLDER_NAME);

    static {
        /*
        Initialize report sub directories
         */
        FRAMES_DIRECTORY.mkdirs();
        METHODS_DIRECTORY.mkdirs();
        SCREENSHOTS_DIRECTORY.mkdirs();
        VIDEO_DIRECTORY.mkdirs();
        XML_DIRECTORY.mkdirs();
    }

    public enum Mode {
        COPY,
        MOVE
    }

    public static File finalizeReport() {
        String relativeReportDirString = PropertyManager.getProperty(TesterraProperties.REPORTDIR, DEFAULT_REPORTDIR);
        File finalReportDirectory = new File(relativeReportDirString);
        try {
            FileUtils.deleteDirectory(finalReportDirectory);
            FileUtils.moveDirectory(REPORT_DIRECTORY, finalReportDirectory);
        } catch (IOException e) {
            throw new TesterraRuntimeException("Could not move report dir: " + e.getMessage(), e);
        }
        return finalReportDirectory;
    }

    /**
     * Adds a screenshot to the report
     * @param screenshotFile The screenshot file
     * @param screenshotSourceFileOrNull The source code of the screenshot origin
     * @param mode
     * @return Screenshot instance
     */
    public static Screenshot provideScreenshot(
        File screenshotFile,
        File screenshotSourceFileOrNull,
        Mode mode
    ) throws IOException {
        if (!screenshotFile.exists()) {
            LOGGER.error("Cannot provide screenshot: " + screenshotFile + " does not exist");
            return null;
        }
        if (screenshotSourceFileOrNull != null && !screenshotSourceFileOrNull.exists()) {
            LOGGER.warn("Cannot provide screenshot source: " + screenshotSourceFileOrNull + " does not exist");
            screenshotSourceFileOrNull = null;
        }

        final Screenshot screenshot = new Screenshot();

        /*
        provide screenshot
         */
        screenshot.filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(screenshotFile.getName());
        screenshot.meta().put(Screenshot.Meta.FILE_NAME.toString(), screenshotFile.getName());
        final File targetScreenshotFile = new File(SCREENSHOTS_DIRECTORY, screenshot.filename);
        switch (mode) {
            case COPY:
                FileUtils.copyFile(screenshotFile, targetScreenshotFile, true);
                break;
            case MOVE:
                FileUtils.moveFile(screenshotFile, targetScreenshotFile);
                break;
        }


        /*
        provide source
         */
        if (screenshotSourceFileOrNull != null) {
            screenshot.sourceFilename = screenshot.filename + ".html";
            screenshot.meta().put(Screenshot.Meta.SOURCE_FILE_NAME.toString(), screenshotSourceFileOrNull.getName());
            final File targetSourceFile = new File(SCREENSHOTS_DIRECTORY, screenshot.sourceFilename);
            switch (mode) {
                case COPY:
                    FileUtils.copyFile(screenshotSourceFileOrNull, targetSourceFile, true);
                    break;
                case MOVE:
                    FileUtils.moveFile(screenshotSourceFileOrNull, targetSourceFile);
                    break;
            }

        }

        LOGGER.info("Provided screenshot " + screenshotFile + " as " + targetScreenshotFile);

        return screenshot;
    }

    public static Video provideVideo(
        File file,
        Mode mode
    ) throws IOException {
        if (!file.exists()) {
            LOGGER.error("Cannot provide video: " + file + " does not exist");
            return null;
        }

        final String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getName());

        File targetFile = new File(VIDEO_DIRECTORY, fileName);
        switch (mode) {
            case COPY:
                FileUtils.copyFile(file, targetFile, true);
                break;
            case MOVE:
                FileUtils.moveFile(file, targetFile);
                break;
        }

        Video video = new Video();
        video.filename = fileName;

        LOGGER.info("Provided video " + file + " as " + targetFile);

        return video;
    }

}
