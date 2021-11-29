package DiscordBot.DiscordBot;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Dotenv dotenv = Dotenv.load();
    	String token = dotenv.get("TOKEN");

    	
    	DiscordClient  client = DiscordClient.create(token);
    	 
    	
    	Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
        gateway.on(MessageCreateEvent.class, event -> {
          Message message = event.getMessage();
          
          if (message.getContent().equalsIgnoreCase("!ping")) {
              return message.getChannel()
                  .flatMap(channel -> channel.createMessage("pong!"));
          }

          return Mono.empty();
        }));
    	

    }
}
