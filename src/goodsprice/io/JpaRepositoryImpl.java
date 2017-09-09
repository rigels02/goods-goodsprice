
package goodsprice.io;

import java.io.IOException;
import java.util.List;


public class JpaRepositoryImpl<T> implements IJpaRepository<T> {

    private IEntityManager em;

    public JpaRepositoryImpl(IEntityManager em) {
        this.em = em;
    }
    
    


    @Override
    public void saveList(List<T> ListOfObjects) throws IOException {
     if (em != null) {
            em.getTransaction().begin();
            for (T ListOfObject : ListOfObjects) {
                em.persist(ListOfObject);
            }
            em.getTransaction().commit();
        }    
    }

    @Override
    public List<T> readListDB() {
    List<T> ListOfObjects=null;
        if (em != null) {
            em.getTransaction().begin();
           
            ListOfObjects= em.findAll();
            em.getTransaction().commit();
        }

        
        return ListOfObjects;  
    }
    
}
