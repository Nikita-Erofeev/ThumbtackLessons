package net.thumbtack.school.file;


import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.ColoredRectangle;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import com.google.gson.Gson;

import java.io.*;


public class FileService {

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException{
        try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
            fos.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        final int SIZE = 6;
        try (FileInputStream fis = new FileInputStream(new File(fileName))) {
            byte[] array = new byte[SIZE];
            fis.read(array);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        final int SIZE = 6;
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] array = new byte[SIZE];
            fis.read(array);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        byte[] byteArray = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            baos.write(array);
            byteArray = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(byteArray)) {
            bais.read(byteArray);
            int size = (byteArray.length % 2 == 0) ? byteArray.length / 2 : byteArray.length / 2 + 1;
            byte[] result = new byte[size];
            int j = 0;
            for (int i = 0; i < byteArray.length; i += 2) {
                result[j] = byteArray[i];
                j++;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException{
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)))) {
            bos.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            bos.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException{
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)))) {
            final int SIZE = 6;
            byte[] array = new byte[SIZE];
            bis.read(array);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException{
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            final int SIZE = 6;
            byte[] array = new byte[SIZE];
            bis.read(array);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] intToByteArray(int value) throws IOException{
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }

    private static int byteToInt(byte[] bytes) {
        int i = ((bytes[0] & 0xFF) << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
        return i;
    }

    public static void writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            byte[] coordinates = new byte[16];
            byte[] leftTopX = intToByteArray(rect.getTopLeft().getX());
            byte[] leftTopY = intToByteArray(rect.getTopLeft().getY());
            byte[] rightBottomX = intToByteArray(rect.getBottomRight().getX());
            byte[] rightBottomY = intToByteArray(rect.getBottomRight().getY());
            for (int i = 0; i < 16; i++) {
                if (i < 4)
                    coordinates[i] = leftTopX[i];
                if (i < 8 && i > 3)
                    coordinates[i] = leftTopY[i - 4];
                if (i < 12 && i > 7)
                    coordinates[i] = rightBottomX[i - 8];
                if (i > 11)
                    coordinates[i] = rightBottomY[i - 12];
            }
            dos.write(coordinates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle readRectangleFromBinaryFile(File file) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            byte[] coord = new byte[16];
            dis.read(coord);
            byte[] leftTopX = new byte[4];
            byte[] leftTopY = new byte[4];
            byte[] rightBottomX = new byte[4];
            byte[] rightBottomY = new byte[4];
            for (int i = 0; i < 16; i++) {
                if (i < 4)
                    leftTopX[i] = coord[i];
                if (i > 3 && i < 8)
                    leftTopY[i - 4] = coord[i];
                if (i < 12 && i > 7)
                    rightBottomX[i - 8] = coord[i];
                if (i > 11)
                    rightBottomY[i - 12] = coord[i];
            }
            Rectangle rect = new Rectangle(byteToInt(leftTopX), byteToInt(leftTopY), byteToInt(rightBottomX), byteToInt(rightBottomY));
            return rect;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeColoredRectangleToBinaryFile(File file, ColoredRectangle rect) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            byte[] coordinates = new byte[16];
            byte[] leftTopX = intToByteArray(rect.getTopLeft().getX());
            byte[] leftTopY = intToByteArray(rect.getTopLeft().getY());
            byte[] rightBottomX = intToByteArray(rect.getBottomRight().getX());
            byte[] rightBottomY = intToByteArray(rect.getBottomRight().getY());
            for (int i = 0; i < 16; i++) {
                if (i < 4)
                    coordinates[i] = leftTopX[i];
                if (i < 8 && i > 3)
                    coordinates[i] = leftTopY[i - 4];
                if (i < 12 && i > 7)
                    coordinates[i] = rightBottomX[i - 8];
                if (i > 11)
                    coordinates[i] = rightBottomY[i - 12];
            }
            dos.write(coordinates);
            dos.writeUTF(rect.getColor().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ColoredRectangle readColoredRectangleFromBinaryFile(File file) throws ColorException, IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            byte[] coord = new byte[16];
            byte[] forColor = new byte[5];
            dis.read(coord, 0, 16);
            String color = dis.readUTF();
            byte[] leftTopX = new byte[4];
            byte[] leftTopY = new byte[4];
            byte[] rightBottomX = new byte[4];
            byte[] rightBottomY = new byte[4];
            for (int i = 0; i < 16; i++) {
                if (i < 4)
                    leftTopX[i] = coord[i];
                if (i > 3 && i < 8)
                    leftTopY[i - 4] = coord[i];
                if (i < 12 && i > 7)
                    rightBottomX[i - 8] = coord[i];
                if (i > 11)
                    rightBottomY[i - 12] = coord[i];
            }
            ColoredRectangle rect = new ColoredRectangle(byteToInt(leftTopX), byteToInt(leftTopY), byteToInt(rightBottomX), byteToInt(rightBottomY), color);
            return rect;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeRectangleArrayToBinaryFile(File file, Rectangle[] rects) throws IOException {
        int SIZE = rects.length;
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            byte[] coords = new byte[SIZE * 16];
            byte[] buffer = new byte[4];
//            ArrayList coords = new ArrayList();
            int j = 0;
            for (Rectangle rect : rects) {
                buffer = intToByteArray(rect.getTopLeft().getX());
                for (int i = 0; i < 4; i++) {
                    coords[j] = buffer[i];
                    j++;
                }
                buffer = intToByteArray(rect.getTopLeft().getY());
                for (int i = 0; i < 4; i++) {
                    coords[j] = buffer[i];
                    j++;
                }
                buffer = intToByteArray(rect.getBottomRight().getX());
                for (int i = 0; i < 4; i++) {
                    coords[j] = buffer[i];
                    j++;
                }
                buffer = intToByteArray(rect.getBottomRight().getY());
                for (int i = 0; i < 4; i++) {
                    coords[j] = buffer[i];
                    j++;
                }
            }
            dos.write(coords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle[] readRectangleArrayFromBinaryFileReverse(File file) throws IOException{
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            byte[] first = new byte[4];
            byte[] second = new byte[4];
            byte[] third = new byte[4];
            byte[] fourth = new byte[4];
            int SIZE = dis.available() / 16;
            Rectangle[] rects = new Rectangle[SIZE];
            int i = 0;
            while (dis.available() > 0) {
                dis.read(first);
                dis.read(second);
                dis.read(third);
                dis.read(fourth);
                rects[i] = new Rectangle(byteToInt(first), byteToInt(second), byteToInt(third), byteToInt(fourth));
                i++;
            }
            int n = SIZE - 1;
            Rectangle myRect;
            for (int j = 0; j < SIZE / 2; j++) {
                myRect = rects[j];
                rects[j] = rects[n];
                rects[n] = myRect;
                n--;
            }
            return rects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            String line = new String();
            line = Integer.toString(rect.getTopLeft().getX()) + " " + Integer.toString(rect.getTopLeft().getY()) +
                    " " + Integer.toString(rect.getBottomRight().getX()) + " " + Integer.toString(rect.getBottomRight().getY());
            osw.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle readRectangleFromTextFileOneLine(File file) throws IOException{
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
            int size = 23;
            char[] buf = new char[size];
            isr.read(buf);
            char[] numbers = new char[(size - 3) / 4];
            int[] points = new int[4];
            int j = 0, n = 0;
            for (int i = 0; i < size; i++) {
                if (buf[i] != ' ') {
                    numbers[j] = buf[i];
                    j++;
                } else {
                    j = 0;
                    points[n] = Integer.parseInt(new String(numbers));
                    n++;
                }
            }
            points[3] = Integer.parseInt(new String(numbers));
            Rectangle rect = new Rectangle(points[0], points[1], points[2], points[3]);
            return rect;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            String[] lines = new String[4];
            lines[0] = Integer.toString(rect.getTopLeft().getX());
            lines[1] = Integer.toString(rect.getTopLeft().getY());
            lines[2] = Integer.toString(rect.getBottomRight().getX());
            lines[3] = Integer.toString(rect.getBottomRight().getY());
            for (String line : lines) {
                osw.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle readRectangleFromTextFileFourLines(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String[] lines = new String[4];
            lines[0] = br.readLine();
            lines[1] = br.readLine();
            lines[2] = br.readLine();
            lines[3] = br.readLine();
            int[] coords = new int[4];
            for (int i = 0; i < 4; i++) {
                coords[i] = Integer.parseInt(lines[i]);
            }
            Rectangle rect = new Rectangle(coords[0], coords[1], coords[2], coords[3]);
            return rect;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            osw.write(trainee.getFirstName() + " " + trainee.getLastName() + " " + Integer.toString(trainee.getRating()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws TrainingException, IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line = br.readLine();
            char[] arr = line.toCharArray();
            String[] names = new String[2];
            StringBuilder sb = new StringBuilder();
            int j = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != ' ') {
                    sb.append(arr[i]);
                } else {
                    names[j] = sb.toString();
                    sb.delete(0, i);
                    j++;
                }
            }
            int rating = Integer.parseInt(sb.toString());
            return new Trainee(names[0], names[1], rating);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            bw.write(trainee.getFirstName());
            bw.newLine();
            bw.write(trainee.getLastName());
            bw.newLine();
            bw.write(Integer.toString(trainee.getRating()));
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Trainee readTraineeFromTextFileThreeLines(File file) throws TrainingException, IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String first = bf.readLine();
            String second = bf.readLine();
            String t = bf.readLine();
//            int r = Integer.parseInt(bf.readLine());
            return new Trainee(first, second, Integer.valueOf(t));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(trainee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String serializeTraineeToJsonString(Trainee trainee) {
        Gson gson = new Gson();
        String jsonText = gson.toJson(trainee);
        return jsonText;
    }

    public static Trainee deserializeTraineeFromJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            Gson gs = new Gson();
            gs.toJson(trainee,bw);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            Gson gs = new Gson();
            return gs.fromJson(br,Trainee.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
