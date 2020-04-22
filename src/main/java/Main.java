/**
 * @(#)Main.java, Apr 08, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangsy
 */
public class Main {

    /**
     * 指定需要提取的书的书名
     */
    public static String BOOK_TITLE =
            "如何学习 ([美]本尼迪克特·凯里)";
//            "The Bullet Journal Method: Track Your Past, Order Your Present, Plan Your Future (Carroll, Ryder)";

    /**
     * 指定导出文件的名称
     */
    public static final String FILE_NAME = "How To Learn";

    public static void main(String[] args) throws Exception {
        String data = readFileAsString("/Users/wangsy/Desktop/My Clippings.txt");

        List<Note> parsedNotes = getNotes(data);

        writeToFile(parsedNotes);
    }

    public static String readFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static List<Note> getNotes(String data) throws ParseException {
        List<Note> parsedNotes = new ArrayList<>();
        String[] notes = data.split("\n==========\n");

        for (String note : notes) {
            String[] lines = note.split("\n");

            String bookTitle = lines[0];
//            System.out.println("bookTitle = " + bookTitle);

            if (bookTitle.contains(BOOK_TITLE)) {
                /** info:
                 * - Your Highlight on Location 85-86 | Added on Wednesday, August 14, 2019 8:14:00 PM*/
                String info = lines[1];

                /** 将 info 分成 location 和 time 两部分*/
                String[] splits = info.split("\\|");

                /** 处理时间
                 * 将" Added on Saturday, September 28, 2019 6:46:03 PM" 转换成："2017-11-13 09:09:00*/
                String timeString = splits[1].substring(10);
                String timestamp = formatTime(timeString);

                Note parsedNote = Note.builder()
                        .bookTitle(bookTitle)
                        .info(info)
                        .location(splits[0])
                        .timestamp(timestamp)
                        .highlight(lines[3])
                        .build();

                parsedNotes.add(parsedNote);
            }
        }
        return parsedNotes;
    }

    private static void writeToFile(List<Note> parsedNotes) throws IOException {
        StringBuffer sb = new StringBuffer();

        sb.append("# " + BOOK_TITLE);
        sb.append("\n");

        parsedNotes.forEach(note -> {
            sb.append("> ");
            sb.append(note.getHighlight());
            sb.append("\n");
            sb.append("> "+note.getLocation().substring(20) + "| Added on " + note.getTimestamp());
            sb.append("\n");
//            sb.append("-----");
            sb.append("\n\n");
        });

        String string = sb.toString();

        FileWriter myWriter = new FileWriter(FILE_NAME + ".md");
        myWriter.write(string);
        myWriter.close();
    }

    private static String formatTime(String timestamp) throws ParseException {
        String originalPattern = "EE, MMMM dd, yyyy hh:mm:ss a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(originalPattern);
        Date date = simpleDateFormat.parse(timestamp);

        String newPattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat(newPattern);
        String newDate = newSimpleDateFormat.format(date);
        return newDate;
    }
}