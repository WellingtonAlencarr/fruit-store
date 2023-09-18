package com.wellington.test.fruits;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class FruitService {

    private final FruitRepository fruitRepository;

    @Inject
    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public List<Fruit> list() {
        return fruitRepository.listAll();
    }

    public Fruit findById(long id) {
        return fruitRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void create(Fruit fruit) {
        fruitRepository.persist(fruit);
    }

    @Transactional
    public void update(long id, Fruit fruit) {
        fruit.setId(id);
        fruitRepository.persist(fruitRepository.getEntityManager().merge(fruit));
    }

    @Transactional
    public void delete(long id) {
        fruitRepository.deleteById(id);
    }
}
