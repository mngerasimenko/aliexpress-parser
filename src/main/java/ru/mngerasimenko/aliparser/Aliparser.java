package ru.mngerasimenko.aliparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mngerasimenko.aliparser.exception.CSVFileException;
import ru.mngerasimenko.aliparser.exception.HTTPQueryException;
import ru.mngerasimenko.aliparser.service.ParserFromUrl;
import ru.mngerasimenko.aliparser.service.ToCsv;


import java.io.IOException;

public class Aliparser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aliparser.class);

    public static void main(String[] args) {
        LOGGER.info("Parser is started");
        String fileName = (args.length == 0) ? "Aliexpress.csv" : args[0];
        try {
            ParserFromUrl parser = new ParserFromUrl();
            ToCsv.saveToCsv(parser.getProductSet(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HTTPQueryException e) {
            LOGGER.error("URL connection error");
        } catch (CSVFileException e) {
            LOGGER.error("File is not available");
        }
    }
}
