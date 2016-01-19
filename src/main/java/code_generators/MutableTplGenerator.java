package code_generators;

import com.google.common.collect.ImmutableList;
import io.crm.util.SimpleCounter;
import io.crm.util.Util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by someone on 09/11/2015.
 */
public class MutableTplGenerator {
    public static void main(String... args) throws Exception {
        String dir = "D:\\IdeaProjects\\crm-common\\src\\main\\java\\io\\crm\\util\\touple";

        Files.createDirectories(Paths.get(dir));
        String[] strings = {"Bi", "Tri", "Quad", "Peta", "Hexa", "Seven", "Octa"};

        final SimpleCounter cc = new SimpleCounter(2);
        Arrays.asList(strings).forEach(nm -> {
            String fs = "package io.crm.util.touple;\n" +
                    "\n" +
                    "import io.crm.intfs." + nm + "ConsumerUnchecked;\n" +
                    "import io.crm.intfs." + nm + "FunctionUnchecked;\n" +
                    "import io.crm.util.touple.immutable.Tpl" + cc.counter + ";" +
                    "import io.crm.util.touple.immutable.Tpls;" +
                    "\n" +
                    "/**\n" +
                    " * Created by someone on 08/11/2015.\n" +
                    " */\n" +
                    "final public class MutableTpl" + cc.counter + "<" + ts(cc.counter) + "> {\n" +
                    declareVals(1, cc.counter) +
                    "\n" + "public MutableTpl" + cc.counter + "() {}\n" +
                    "    public MutableTpl" + cc.counter + "(" + tsv(cc.counter) + ") {\n" +
                    setVals(1, cc.counter) +
                    "    }\n" +
                    "\n" +
                    al(cc, "ps") +
                    "    public MutableTpl" + (cc.counter - 1) + "<" + ts((cc.counter - 1)) + "> pp() {\n" +
                    "        return new MutableTpl" + (cc.counter - 1) + "<>(" + vs((cc.counter - 1)) + ");\n" +
                    "    }\n" +
                    "\n" +
                    al(cc, "al") +
                    "\n" +
                    af(cc) +
                    "\n" +
                    "    public MutableTpl" + (cc.counter - 1) + "<" + ts(2, cc.counter) + "> df() {\n" +
                    "        return new MutableTpl" + (cc.counter - 1) + "<>(" + vs(2, cc.counter) + ");\n" +
                    "    }\n" +
                    "\n" +
                    "    public MutableTpl" + (cc.counter - 1) + "<" + ts((cc.counter - 1)) + "> dl() {\n" +
                    "        return new MutableTpl" + (cc.counter - 1) + "<>(" + vs((cc.counter - 1)) + ");\n" +
                    "    }\n" +
                    "\n" +
                    "    public T1 ft() {\n" +
                    "        return t1;\n" +
                    "    }\n" +
                    "\n" +
                    "    public T" + cc.counter + " lt() {\n" +
                    "        return t" + cc.counter + ";\n" +
                    "    }\n" +
                    "\n" +
                    "    public Tpl" + cc.counter + "<" + ts(cc.counter) + "> toImmutable() {\n" +
                    "        return Tpls.of(" + vs(cc.counter) + ");\n" +
                    "    }\n" +
                    "    public <R> R apply(final " + nm + "FunctionUnchecked<" + ts(cc.counter) + ", R> functionUnchecked) {\n" +
                    "        try {\n" +
                    "            return functionUnchecked.apply(" + vs(cc.counter) + ");\n" +
                    "        } catch (Exception e) {\n" +
                    "            if (e instanceof RuntimeException) {\n" +
                    "                throw (RuntimeException) e;\n" +
                    "            } else {\n" +
                    "                throw new RuntimeException(e);\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    public void accept(final " + nm + "ConsumerUnchecked<" + ts(cc.counter) + "> consumerUnchecked) {\n" +
                    "        try {\n" +
                    "            consumerUnchecked.accept(" + vs(cc.counter) + ");\n" +
                    "        } catch (Exception e) {\n" +
                    "            if (e instanceof RuntimeException) {\n" +
                    "                throw (RuntimeException) e;\n" +
                    "            } else {\n" +
                    "                throw new RuntimeException(e);\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    joinsLoop(cc.counter) +
                    getters(cc.counter) +
                    setters(cc.counter) +
                    "}\n";

            Util.accept(new File(dir, "MutableTpl" + cc.counter + ".java"), file -> {
                PrintStream stream = null;
                try {
                    file.createNewFile();
                    stream = new PrintStream(file);
                    stream.print(fs);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    stream.close();
                }
            });

            cc.counter++;
        });
    }

    private static String setters(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            builder.append(setter(i));
        }
        return builder.toString();
    }

    private static String setter(int n) {
        return "    public void setT" + n + "(T" + n + " t" + n + ") {\n" +
                "        this.t" + n + " = t" + n + ";\n" +
                "    }\n";
    }

    private static String getters(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            builder.append(getter(i));
        }
        return builder.toString();
    }

    private static String getter(int n) {
        return "    public T" + n + " getT" + n + "() {\n" +
                "        return t" + n + ";\n" +
                "    }\n";
    }

    private static String af(SimpleCounter cc) {
        if (cc.counter + 1 > 8) return "";
        return "    public <T" + (cc.counter + 1) + "> MutableTpl" + (cc.counter + 1) + "<T" + (cc.counter + 1) + ", " + ts(cc.counter) + "> af(final T" + (cc.counter + 1) + " val) {\n" +
                "        return new MutableTpl" + (cc.counter + 1) + "<>(val, " + vs(cc.counter) + ");\n" +
                "    }\n";
    }

    private static String al(final SimpleCounter cc, String functionName) {
        if (cc.counter + 1 > 8) return "";
        return "    public <T" + (cc.counter + 1) + "> MutableTpl" + (cc.counter + 1) + "<" + ts(cc.counter) + ", T" + (cc.counter + 1) + "> " + functionName + "(final T" + (cc.counter + 1) + " val) {\n" +
                "        return new MutableTpl" + (cc.counter + 1) + "<>(" + vs(cc.counter) + ", val);\n" +
                "    }\n";
    }

    private static String joinsLoop(int n) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (int i = 2; i <= 8; i++) {
            if (i + n > 8) continue;
            builder.add(joins(n, i));
        }
        return String.join("\n\n", builder.build());
    }

    private static String joins(int n, int tSize) {
        String ss = "    public <" + ts(n + 1, n + tSize) + "> MutableTpl" + (n + tSize) + "<" + ts((n + tSize)) + "> jn(final MutableTpl" + tSize + "<" + ts(n + 1, n + tSize) + "> MutableTpl" + tSize + ") {\n" +
                "        return new MutableTpl" + (n + tSize) + "<>(" + vs(n) + ", " + nestedvs(tSize, 1, tSize) + ");\n" +
                "    }";
        return ss;
    }

    private static String nestedvs(int t_size, int s, int e) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (int i = s; i <= e; i++) {
            builder.add("MutableTpl" + t_size + ".t" + i);
        }
        return String.join(", ", builder.build());
    }

    private static String declareVals(int s, int e) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (int i = s; i <= e; i++) {
            builder.add("public T" + i + " t" + i + ";\n");
        }
        return String.join("", builder.build());
    }

    private static String setVals(int s, int e) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (int i = s; i <= e; i++) {
            builder.add("this.t" + i + " = t" + i + ";\n");
        }
        return String.join("", builder.build());
    }

    static String ts(int n) {
        return ts(1, n);
    }

    static String ts(int start, int end) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (int i = start; i <= end; i++) {
            builder.add("T" + i);
        }
        return String.join(", ", builder.build());
    }

    static String vs(int n) {
        return vs(1, n);
    }

    static String vs(int start, int end) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (int i = start; i <= end; i++) {
            builder.add("t" + i);
        }
        return String.join(", ", builder.build());
    }

    static String tsv(int n) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (int i = 1; i <= n; i++) {
            builder.add("final T" + i + " t" + i);
        }
        return String.join(", ", builder.build());
    }
}