package esU4W2D4.entities;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Suppliers {
    private static Faker faker = new Faker();
    private static Random random = new Random();

    public static Supplier<Customer> customerSupplier = () -> {
        String name = faker.name().fullName();
        int tier = random.nextInt(3);
        return new Customer(name, tier);
    };

    public static Supplier<List<Customer>> listaCustomerSupplier = ()-> {
        List<Customer> listaCustomer = new ArrayList<>();
        for(int i = 0; i<10; i++){
            listaCustomer.add(customerSupplier.get());
        }
        return listaCustomer;
    };

}
