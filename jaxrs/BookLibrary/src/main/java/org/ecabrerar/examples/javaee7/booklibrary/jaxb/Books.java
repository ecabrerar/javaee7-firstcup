/*
 * Copyright 2014 ecabrerar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ecabrerar.examples.javaee7.booklibrary.jaxb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.ecabrerar.examples.javaee7.booklibrary.entities.Book;

/**
 *
 * @author ecabrerar
 */
@XmlRootElement
@XmlSeeAlso(Book.class)
public class Books extends ArrayList<Book> {

    private static final long serialVersionUID = 1L;

    public Books() {
        super();
    }

    public Books(Collection<? extends Book> c) {
        super(c);
    }

    @XmlElement(name = "book")
    public List<Book> getBooks() {
        return this;
    }

    public void setBooks(List<Book> books) {
        this.addAll(books);
    }

}
