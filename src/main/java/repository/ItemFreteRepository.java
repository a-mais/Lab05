package repository;

import dao.BaseRepository;
import model.ItemFrete;

public class ItemFreteRepository extends BaseRepository<ItemFrete> {

    public ItemFreteRepository() {
        super(ItemFrete.class);
    }
}
