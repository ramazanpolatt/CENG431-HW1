package com.company.Application;

import java.io.IOException;
import java.util.Collection;

public interface IProductandBaskets {

    Collection<IProduct> getProducts() throws IOException;
    Collection<IBasket> getBaskets() throws IOException;
}
