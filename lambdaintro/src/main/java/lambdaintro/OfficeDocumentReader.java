package lambdaintro;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class OfficeDocumentReader {


    public List<File> listOfficeDocuments(File path) {
        List<File> result = List.of(Objects.requireNonNull(path.listFiles(
                pathname -> pathname.isFile() && (
                        pathname.getName().toLowerCase().endsWith("docx") ||
                        pathname.getName().toLowerCase().endsWith("pptx") ||
                        pathname.getName().toLowerCase().endsWith("xlsx")))));
        return result.stream().sorted(Comparator.comparing(File::getName)).toList();
    }
}
