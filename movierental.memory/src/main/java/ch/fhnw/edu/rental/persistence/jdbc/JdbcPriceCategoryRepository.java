package ch.fhnw.edu.rental.persistence.jdbc;

import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.model.PriceCategoryChildren;
import ch.fhnw.edu.rental.model.PriceCategoryNewRelease;
import ch.fhnw.edu.rental.model.PriceCategoryRegular;
import ch.fhnw.edu.rental.persistence.impl.PriceCategoryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcPriceCategoryRepository extends PriceCategoryRepositoryImpl {

    @Autowired
    private JdbcTemplate template;


    @Override
    public Optional<PriceCategory> findById(Long id) {
        return template.query("SELECT * FROM PRICECATEGORIES WHERE PRICECATEGORY_ID = ?", (rs, row) -> createPriceCategory(rs), id).stream().findFirst();
    }

    @Override
    public List<PriceCategory> findAll() {
        return template.query("SELECT * FROM PRICECATEGORIES", (rs, row) -> createPriceCategory(rs));
    }

    @Override
    public PriceCategory save(PriceCategory priceCategory) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        template.update("DELETE FROM PRICECATEGORIES WHERE PRICECATEGORY_ID =?", id);
    }

    @Override
    public void delete(PriceCategory entity) {
        deleteById(entity.getId());
    }

    @Override
    public boolean existsById(Long aLong) {
        return findById(aLong).isPresent();
    }

    @Override
    public long count() {
        return findAll().stream().count();
    }

    private PriceCategory createPriceCategory(ResultSet rs) throws SQLException {
        PriceCategory priceCategory;

        long id = rs.getLong("pricecategory_id");
        String pricecategoryType = rs.getString("pricecategory_type");

        switch (pricecategoryType) {
            case "Regular":
                priceCategory = new PriceCategoryRegular();
                priceCategory.setId(id);
                return priceCategory;
            case "Children":
                priceCategory = new PriceCategoryChildren();
                priceCategory.setId(id);
                return priceCategory;
            case "NewRelease":
                priceCategory = new PriceCategoryNewRelease();
                priceCategory.setId(id);
                return priceCategory;
            default:
                return null;
        }
    }
}
