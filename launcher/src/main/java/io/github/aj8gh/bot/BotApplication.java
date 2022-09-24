package io.github.aj8gh.bot;

import static org.springframework.context.annotation.ComponentScan.Filter;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.standard.ShellComponent;

@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(ShellComponent.class))
public class BotApplication {

  private static final Logger LOG = LoggerFactory.getLogger(BotApplication.class);

  @Generated
  public static void main(String[] args) {
    LOG.info("*** STARTING BOT ***");
    SpringApplication.run(BotApplication.class, args);
  }
}
