package esU4W2D4;

import esU4W2D4.entities.Category;
import esU4W2D4.entities.Customer;
import esU4W2D4.entities.Order;
import esU4W2D4.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static esU4W2D4.entities.Suppliers.customerSupplier;
import static esU4W2D4.entities.Suppliers.listaCustomerSupplier;

public class Application {
        public  static void main(String[] args){

            //PRODOTTI
            Product b1 = new Product("Mille e una notte", Category.BOOK, 12.5);
            Product b2 = new Product("Paure e deliri", Category.BOOK, 15);
            Product c1 = new Product("T-Shirt bambino", Category.BABY, 12);
            Product c2 = new Product("Zainetto Baby", Category.BABY, 8.50);
            Product c3 = new Product("Cappellino Topolino", Category.BABY, 6);
            Product m1 = new Product("Rasoio elettrico", Category.BOYS, 35);
            Product m2 = new Product("T-Shirt Uomo", Category.BOYS, 19);
            Product m3 = new Product("Pantaloni Cargo", Category.BOYS, 25.5);

            List<Product> prodottiDisponibili = new ArrayList<>();
            Collections.addAll(prodottiDisponibili, b1,b2,c1,c2,c3,m1,m2,m3);

            //CUSTOMER
            Customer user1 = customerSupplier.get();
            Customer user2 = customerSupplier.get();
            Customer user3 = customerSupplier.get();

            List<Customer> userRegistrati = listaCustomerSupplier.get();

            //ORDINI
            Order o1=  new Order(LocalDate.of(2024, 3, 2), LocalDate.of(2024, 3, 10), List.of(b1, c1, m2), user1);
            Order o2=  new Order(LocalDate.of(2025, 9, 18), LocalDate.of(2025, 9, 25), List.of(b2, c3, m3), user1);
            Order o3=  new Order(LocalDate.of(2025, 5, 12), LocalDate.of(2025, 5, 17), List.of(b1, b2, c1, m1), user2);
            Order o4=  new Order(LocalDate.of(2024, 1, 24), LocalDate.of(2024, 2, 2), List.of( c1, c2), user3);
            Order o5 =  new Order(LocalDate.of(2024, 6, 24), LocalDate.of(2024, 6, 30), List.of( b1, m1), user3);

            List<Order>  ordiniComplessivi = new ArrayList<>();
            Collections.addAll(ordiniComplessivi, o1, o2, o3, o4, o5);

            //ESERCIZIO1
            Map<Customer, List<Order>> mapUserOrdini = ordiniComplessivi.stream().collect(Collectors.groupingBy(Order::getCustomer));
            mapUserOrdini.forEach((customer, orders) -> {
                System.out.print(customer.getName() + ": ");
                orders.forEach(order ->
                        System.out.print( order.getId() + " " + order.getStatus() + " ")
                );
                System.out.println(" ");
            });

            //ESERCIZIO2
            System.out.println("\n");
            Map<Customer, Double> mapUserTotale = ordiniComplessivi.stream()
                    .collect(Collectors.groupingBy(
                            Order::getCustomer,
                            Collectors.summingDouble(order ->
                                    order.getProducts().stream()
                                            .mapToDouble(Product::getPrice)
                                            .sum()
                            )
                    ));
            mapUserTotale.forEach((user, totale) ->  System.out.println(user.getName() + " tot:  " + totale ));

            //ESERCIZIO3
            System.out.println("\n");
            
        }
    }

