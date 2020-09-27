package com.langel.compress;

import com.langel.compress.support.*;
import com.langel.compress.util.FileUtils;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2019/1/12 10:10 PM
 **/
public class Main {

    public static void main(String[] args) {
        test("Small File", "src/main/resources/small.txt", 10000);
        test("General File", "src/main/resources/general.txt", 1000);
        test("Big File", "src/main/resources/big.txt", 100);
    }

    private static void test(String testTitle, String testFile, int testNum) {

        Snappy snappy = new Snappy();
        ExecuteRecorder snappyExec = new ExecuteRecorder(snappy, snappy);
        Gzip gzip = new Gzip();
        ExecuteRecorder gzipExec = new ExecuteRecorder(gzip, gzip);
        Lz4 lz4 = new Lz4();
        ExecuteRecorder lz4Exec = new ExecuteRecorder(lz4, lz4);
        Bzip bzip = new Bzip();
        ExecuteRecorder bzipExec = new ExecuteRecorder(bzip, bzip);
        Zstd zstd = new Zstd();
        ExecuteRecorder zstdExec = new ExecuteRecorder(zstd, zstd);
        ZstdStream zstdStream = new ZstdStream();
        ExecuteRecorder zstdStreamExec = new ExecuteRecorder(zstdStream,zstdStream);


        System.out.println("\n\n" + testTitle + " :");
        byte[] fileBytes = FileUtils.readToString(testFile).getBytes();

        System.out.println("Snappy :");
        byte[] snappyCompressed = snappyExec.compressMulti(fileBytes, testNum);
        snappyExec.decompressMulti(snappyCompressed, testNum);

//        System.out.println("Gzip :");
//        byte[] gzipCompressed = gzipExec.compressMulti(fileBytes, testNum);
//        gzipExec.decompressMulti(gzipCompressed, testNum);

        System.out.println("Lz4 :");
        byte[] lz4Compressed = lz4Exec.compressMulti(fileBytes, testNum);
        lz4Exec.decompressMulti(lz4Compressed, testNum);

        //        System.out.println("Bzip :");
        //        byte[] bzipCompressed = bzipExec.compressMulti(fileBytes,testNum);
        //        bzipExec.decompressMulti(bzipCompressed,testNum);

        System.out.println("Zstd :");
        byte[] zstdCompressed = zstdExec.compressMulti(fileBytes, testNum);
        zstdExec.decompressMulti(zstdCompressed, testNum);

        //        System.out.println("ZstdStream :");
        //        zstdStreamCompressed = zstdStreamExec.compressMulti(fileBytes, testNum);
        //        zstdExec.decompressMulti(zstdStreamCompressed, testNum);
    }
}
