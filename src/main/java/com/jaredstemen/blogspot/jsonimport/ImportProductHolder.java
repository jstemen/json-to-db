package com.jaredstemen.blogspot.jsonimport;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/18/14
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImportProductHolder {
    ImportProduct[] products;

    public ImportProductHolder() {
    }

    public ImportProductHolder(ImportProduct[] products) {
        this.products = products;
    }

    public ImportProduct[] getProducts() {
        return products;
    }

    public void setProducts(ImportProduct[] products) {
        this.products = products;
    }
}
