package goodsprice.impoexpo;

import goodsprice.io.TxtReaderWriter;
import goodsprice.model.Good;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raitis
 */
public class ExportImport implements IExportImport {

    @Override
    public void exportData(String filePath, List<Good> goods) throws FileNotFoundException, UnsupportedEncodingException {
        TxtReaderWriter rw = new TxtReaderWriter(filePath);
        String forExport = null;
        forExport = CSV.makeCSVString(goods);
        rw.writeToFile(forExport);

    }

    @Override
    public List<Good> importData(String filePath) throws IOException, Exception {
        TxtReaderWriter rw = new TxtReaderWriter(filePath);
        String imported = rw.ReadFromFile();
        List<Good> goods = new ArrayList<>();
        goods = CSV.makeListFromCSVString(imported);
        return goods;
    }

}
