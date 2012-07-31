/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nunenuh.bind.util;

import java.io.*;

/**
 *
 * @author nunenuh
 */
public class ZoneOperation {

    public static int countLine(String path) {
        try {
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                LineNumberReader ln = new LineNumberReader(br);

                int c = 1;
                while (ln.readLine() != null) {
                    c++;
                }
                ln.close();
                br.close();
                fr.close();
                return c;
            } else {
                return 0;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static String readByLine(String path, int lineToRead) {
        try {
            //prepare for reading file
            File f = new File(path);
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                //read file and get line
                String line;
                String getLine = null;
                int c = 1;
                while ((line = br.readLine()) != null) {
                    if (c == lineToRead) {
                        getLine = line;
                    }
                    c++;
                }

                return getLine;
            } else {
                System.out.println("file Not Exist and is Directory");
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean replaceByLine(String path, int lineToModified, String replaceString) {
        try {
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                //read file object created
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                //dumping file content to temporary array
                StringBuilder sb = new StringBuilder();
                String line;
                int c = 1;
                while ((line = br.readLine()) != null) {
                    if (c == lineToModified) {
                        sb.append(replaceString).append("\n");
                    } else {
                        sb.append(line).append("\n");
                    }
                    c++;
                }


                //writing file to text
                FileWriter fw = new FileWriter(f);
                fw.write(sb.toString());
                fw.close();
                fr.close();
                return true;

            } else {
                return false;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean insertNewLine(String path, String content) {
        try {
            File f = new File(path);
            StringBuilder sb = new StringBuilder();
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fstream = new FileInputStream(path);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String strLine;
                StringBuilder out = new StringBuilder();

                while ((strLine = br.readLine()) != null) {
                    out.append(strLine);
                    out.append("\n");
                }
                in.close();
                out.append(content);

                Writer output = new BufferedWriter(new FileWriter(f));
                output.write(out.toString());
                output.close();



                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean removeByLine(String path, int lineNumber) {
        try {
            //prepare for reading file
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();

                //read the file and push to StringBuilder
                String line;
                int c = 1;
                while ((line = br.readLine()) != null) {
                    if (c != lineNumber) {
                        sb.append(line).append("\n");
                    }
                    c++;
                }

                //writing file to text
                FileWriter fw = new FileWriter(f);
                fw.write(sb.toString());
                fw.close();
                fr.close();
                return true;
            } else {
                return false;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean removeLine(String path, int startLine, int endLine) {
        try {
            //prepare for reading file
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();

                //read the file and push to StringBuilder
                String line;
                int c = 1;
                while ((line = br.readLine()) != null) {
                    if (c != startLine) {
                        sb.append(line).append("\n");
                    } else {
                        if (startLine < endLine) {
                            startLine++;
                        }
                    }
                    c++;
                }

                //writing file to text
                FileWriter fw = new FileWriter(f);
                fw.write(sb.toString());
                fw.close();
                fr.close();
                return true;
            } else {
                return false;
            }

        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean insertByLine(String path, String lineToInsert, int lineNumber) {
        try {
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();

                String line;
                int c = 1;
                while ((line = br.readLine()) != null) {
                    if (c != lineNumber) {
                        sb.append(line).append("\n");
                    } else {
                        if (!line.isEmpty()) {
                            sb.append(line).append("\n");
                            sb.append(lineToInsert).append("\n");
                        } else {
                            sb.append(lineToInsert).append("\n");
                        }

                    }
                    c++;
                }

                //prepare for writing to files
                FileWriter fw = new FileWriter(f);
                fw.write(sb.toString());
                fw.close();
                br.close();
                fr.close();

                return true;
            } else {
                return false;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean removeEmptyLines(String path) {
        try {
            File f = new File(path);
            if (f.exists() && !f.isDirectory()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();

                String line;
                int c = 1;
                while ((line = br.readLine()) != null) {

                    if (!line.isEmpty()) {
                        sb.append(line).append("\n");
                    }
                    c++;
                }

                //prepare for writing to files
                FileWriter fw = new FileWriter(f);
                fw.write(sb.toString());
                fw.close();
                br.close();
                fr.close();

                return true;
            } else {
                return false;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
