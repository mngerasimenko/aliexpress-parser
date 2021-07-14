package ru.mngerasimenko.aliparser.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mngerasimenko.aliparser.domain.JProduct;
import ru.mngerasimenko.aliparser.exception.CSVFileException;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ToCsv {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToCsv.class);

    public static void saveToCsv(Set<JProduct> productSet, String fileName) throws CSVFileException {

        try (FileWriter writer = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader(
                     "productId", "sellerId", "oriMinPrice", "oriMaxPrice",
                     "promotionId", "startTime", "endTime", "phase",
                     "productTitle", "minPrice", "maxPrice", "discount",
                     "totalStock", "stock", "orders", "soldout",
                     "productImage", "productDetailUrl", "shopUrl",
                     "trace", "totalTranpro3", "productPositiveRate",
                     "productAverageStar", "itemEvalTotalNum", "gmtCreate"));) {
            int count = 0;
            for (JProduct p : productSet) {
                count++;
                printer.printRecord(p.getProductId(), p.getSellerId(), p.getOriMaxPrice(),
                        p.getOriMaxPrice(), p.getPromotionId(), p.getStartTime(),
                        p.getEndTime(), p.getPhase(), p.getProductTitle(),
                        p.getMinPrice(), p.getMaxPrice(), p.getDiscount(),
                        p.getTotalStock(), p.getStock(), p.getOrders(), p.getSoldout(),
                        p.getProductImage(), p.getProductDetailUrl(), p.getShopUrl(),
                        p.getTrace(), p.getTotalTranpro3(), p.getProductPositiveRate(),
                        p.getProductAverageStar(), p.getItemEvalTotalNum(), p.getGmtCreate());
                if (count==100) break;
            }
            printer.flush();
            LOGGER.info("Write result to file {}", fileName);
        } catch (IOException e) {
            throw new CSVFileException("File error", e);
        }
    }
}
