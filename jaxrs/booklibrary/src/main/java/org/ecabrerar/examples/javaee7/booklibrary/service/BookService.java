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
package org.ecabrerar.examples.javaee7.booklibrary.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;
import org.ecabrerar.examples.javaee7.booklibrary.entities.Book;

/**
 *
 * @author ecabrerar
 */
@Stateless
public class BookService {

    @PersistenceContext(unitName = "BookLibraryPU")
    EntityManager entityManager;

    public Book createBook(Book book) {

        if (book == null) {
            throw new ValidationException("Book object is null");
        }

        entityManager.persist(book);

        return book;
    }

    public void deleteBook(String id) {
        
        if (id == null) {
            throw new ValidationException("Invalid book id");
        }
        
        entityManager.remove(findBook(id));
    }

    public Book getBook(String id) {

        if (id == null) {
            throw new ValidationException("Invalid book id");
        }

        return findBook(id);

    }
    
    private Book findBook(String id){
        
        if (id == null) {
            throw new ValidationException("Invalid book id");
        }
        
         return entityManager.find(Book.class, Integer.parseInt(id));        
    }
    
    public List<Book> findAllBooks(){
       TypedQuery<Book> typedQuery = entityManager.createNamedQuery("Book.findAll", Book.class);
       return typedQuery.getResultList();
             
    }

}
