package pe.com.mallgp.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.com.mallgp.backend.entities.*;
import pe.com.mallgp.backend.repositories.*;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
		AdminRepository adminRepository,
		MallRepository mallRepository,
		NewRepository newRepository,
		OfferRepository offerRepository,
		ProductRepository productRepository,
		StoreRepository storeRepository,
		ProductStoreRepository productStoreRepository,
		StoreMallRepository storeMallRepository,
		SuggestionRepository suggestionRepository
		){
		return args->{
			//admins
			Admin admin=new Admin("Luis","123456");
			adminRepository.save(admin);

			Admin admin1=new Admin("Gabriel","123456");
			adminRepository.save(admin1);

			Admin admin2=new Admin("Edwin","123456");
			adminRepository.save(admin2);

			//malls
			Mall mall=new Mall("Larcomar","Miraflores","Malecon de la Reserva 610, Miraflores 15074","larcomar.jpg");
			mallRepository.save(mall);
			newRepository.save(new New("Martes cine 50% descuento","19/11/2022" , "19/11/2022", mall));
			newRepository.save(new New("Jueves cine 50% descuento", "19/11/2022","19/11/2022" , mall));


			Mall mall1=new Mall("Plaza San Miguel","San Miguel","Lima 32, San Miguel 15088","plaza_san_miguel.jpg");
			mallRepository.save(mall1);
			newRepository.save(new New("Novedad 2","19-11-2022","19-11-2022", mall1));
			newRepository.save(new New("Novedad 3", "19-11-2022","19-11-2022", mall1));

			Mall mall2=new Mall("Mall del Sur","San Juan de Miraflores","Avenida Los Lirios 310, San Juan de Miraflores 15801","mall_del_sur.webp");
			mallRepository.save(mall2);
			newRepository.save(new New("Novedad 4", "19-11-2022", "19-11-2022", mall2));
			newRepository.save(new New("Novedad 5", "19-11-2022", "19-11-2022", mall2));

			Mall mall3=new Mall("Plaza Norte","Independencia","Av. Tomas Valle, Independencia 15311","plaza_norte.jpg");
			mallRepository.save(mall3);

			Mall mall4=new Mall("Jockey Plaza","Santiago de Surco","Psje. Ontario, Santiago de Surco 15023","jockey_plaza.jpg");
			mallRepository.save(mall4);

			Mall mall5=new Mall("Mega Plaza","Independencia","Av. Alfredo Mendiola 3698, Independencia 15311","mega_plaza.jpg");
			mallRepository.save(mall5);

			//products
			Product product=new Product("Zapatilla","Calzado","A","25","Unisex","ZpatillaFluidflow.png");
			productRepository.save(product);

			Product product1=new Product("Zapatilla","Calzado","A","25","Unisex","Zpatilla4D.png");
			productRepository.save(product1);

			Product product2=new Product("Zapatilla","Calzado","A","25","Unisex","ZpatillaSolarg.png");
			productRepository.save(product2);

			Product product3=new Product("Zapatilla","Calzado","A","25","Unisex","ZpatillaEnergy.png");
			productRepository.save(product3);

			Product product4=new Product("Zapatilla","Calzado","A","25","Unisex","ZpatillaNdm.png");
			productRepository.save(product4);

			Product product5=new Product("Zapatilla","Calzado","A","25","Unisex","ZpatillaMountain.png");
			productRepository.save(product5);


			//stores
			Store store=new Store("Skechers","calzado","10am - 10pm","Piso 1","skechers.png");
			storeRepository.save(store);

			Store store1=new Store("H&M","ropa","10am - 10pm","Piso 2","hym.jpeg");
			storeRepository.save(store1);

			Store store2=new Store("Nike","deportes","10am - 10pm","Piso 1","nike.webp");
			storeRepository.save(store2);

			Store store3=new Store("Marathon","deportes","10am - 10pm","Piso 3","marathon.jpg");
			storeRepository.save(store3);

			Store store4=new Store("Bata","calzado","10am - 10pm","Piso 2","Bata.jpg");
			storeRepository.save(store4);

			Store store5=new Store("Saga Falabella","ropa","10am - 10pm","Piso 3","saga.jpg");
			storeRepository.save(store5);

			//ProductStore
			ProductStore productStore1=new ProductStore(product,store,12.0,"19-11-2022");
			productStoreRepository.save(productStore1);

			//offers
			offerRepository.save(new Offer("Larcomar","Zapatillas Fluidflow 2.0","Mujer",
					"S/. 289","S/. 231.20","18-11","22-11","ZpatillaFluidflow.png",
					store,product));
			offerRepository.save(new Offer("Larcomar","Zapatillas ultra 4D","Hombre","S/. 899","S/. 719.20","18-11","22-11","Zpatilla4D.png",store1,product1));
			offerRepository.save(new Offer("Larcomar","Zapatillas Solarglide 5","Mujer","S/. 499","S/. 399.20","18-11","22-11","ZpatillaSolarg.png",store2,product2));
			offerRepository.save(new Offer("Larcomar","Zapatillas Energyfalcon","Hombre","S/. 299","S/. 223.20","18-11","22-11","ZpatillaEnergy.png",store,product));
			offerRepository.save(new Offer("Larcomar","Zapatillas Nmd_R1","Mujer","S/. 499","S/. 399.20","18-11","22-11","ZpatillaNdm.png",store1,product1));
			offerRepository.save(new Offer("Larcomar","Zapatillas de Mountain Bike Five Ten Impact Pro","Hombre","S/. 649","S/. 454.30","18-11","22-11","ZpatillaMountain.png",store2,product2));


			//StoreMall
			StoreMall storeMall1=new StoreMall(store,mall,120,admin);
			storeMallRepository.save(storeMall1);

			//Suggestion
			Suggestion suggestion1=new Suggestion("Pablo","Ponganle juegos >:0", "19-11-2022");
			suggestionRepository.save(suggestion1);
		};
	}

}
