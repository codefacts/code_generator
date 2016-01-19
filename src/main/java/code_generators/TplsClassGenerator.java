package code_generators;

import io.crm.util.Util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static code_generators.TplGenerators.ts;
import static code_generators.TplGenerators.tsv;
import static code_generators.TplGenerators.vs;

/**
 * Created by someone on 09/11/2015.
 */
public class TplsClassGenerator {
    public static void main(String... args) throws Exception {
        String dir = "D:\\IdeaProjects\\crm-common\\src\\main\\java\\io\\crm\\util\\touple\\immutable";

        Files.createDirectories(Paths.get(dir));

        final StringBuilder builder = new StringBuilder();
        builder.append("package io.crm.util.touple.immutable;\n\n");
        builder.append("final public class Tpls {");
        for (int i = 2; i <= 8; i++) {
            builder.append(tpl(i));
        }
        builder.append("    }\n");

        Util.accept(new File(dir, "Tpls" + ".java"), file -> {
            PrintStream stream = null;
            try {
                file.createNewFile();
                stream = new PrintStream(file);
                stream.print(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                stream.close();
            }
        });
    }

    private static String tpl(int n) {
        return "public static <" + ts(n) + "> Tpl" + n + "<" + ts(n) + "> of(" + tsv(n) + ") {\n" +
                "        return new Tpl" + n + "<>(" + vs(n) + ");\n" +
                "    }\n";
    }
}
