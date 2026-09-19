package cz.roman.spanek.anv.y2026.pr01.antipatterns.priklad3;

import lombok.Getter;

import java.util.*;
import java.util.function.*;

public class GreetingService {

    public static void main(String[] args) {
        GreetingFacade facade = GreetingFacadeFactory.getInstance()
                .createFacade(GreetingStrategyType.STANDARD);

        GreetingRequest request = new GreetingRequestBuilder()
                .withName("Alice")
                .withTimeOfDay(TimeOfDay.MORNING)
                .withPoliteness(PolitenessLevel.CASUAL)
                .build();

        GreetingContext context = new GreetingContext(request);
        String result = facade.generate(context);

        System.out.println(result);
    }
}

enum TimeOfDay { MORNING, AFTERNOON, EVENING }
enum PolitenessLevel { CASUAL, FORMAL }
enum GreetingStrategyType { STANDARD, VERBOSE }

@Getter
class GreetingRequest {
    private final String name;
    private final TimeOfDay timeOfDay;
    private final PolitenessLevel politenessLevel;

    GreetingRequest(String name, TimeOfDay timeOfDay, PolitenessLevel politenessLevel) {
        this.name = name;
        this.timeOfDay = timeOfDay;
        this.politenessLevel = politenessLevel;
    }

}

class GreetingRequestBuilder {
    private String name;
    private TimeOfDay timeOfDay = TimeOfDay.MORNING;
    private PolitenessLevel politenessLevel = PolitenessLevel.CASUAL;

    public GreetingRequestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GreetingRequestBuilder withTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
        return this;
    }

    public GreetingRequestBuilder withPoliteness(PolitenessLevel level) {
        this.politenessLevel = level;
        return this;
    }

    public GreetingRequest build() {
        return new GreetingRequest(name, timeOfDay, politenessLevel);
    }
}

class GreetingContext {
    @Getter
    private final GreetingRequest request;
    private final Map<String, Object> attributes = new HashMap<>();

    GreetingContext(GreetingRequest request) {
        this.request = request;
        attributes.put("createdAt", System.currentTimeMillis());
    }

    public Object getAttribute(String key) { return attributes.get(key); }
}

interface GreetingStrategy {
    String buildGreeting(GreetingContext context);
}

class StandardGreetingStrategy implements GreetingStrategy {
    @Override
    public String buildGreeting(GreetingContext context) {
        GreetingRequest req = context.getRequest();
        String salutation = req.getTimeOfDay() == TimeOfDay.MORNING ? "Good morning" :
                req.getTimeOfDay() == TimeOfDay.AFTERNOON ? "Good afternoon" : "Good evening";
        return salutation + ", " + req.getName() + "!";
    }
}

class VerboseGreetingStrategy implements GreetingStrategy {
    @Override
    public String buildGreeting(GreetingContext context) {
        GreetingRequest req = context.getRequest();
        return new StandardGreetingStrategy().buildGreeting(context)
                + " We hope you are having a pleasant " + req.getTimeOfDay().toString().toLowerCase() + ".";
    }
}

interface GreetingStrategyProvider {
    GreetingStrategy getStrategy(GreetingStrategyType type);
}

class GreetingStrategyProviderImpl implements GreetingStrategyProvider {
    private final Map<GreetingStrategyType, Supplier<GreetingStrategy>> registry = new HashMap<>();

    GreetingStrategyProviderImpl() {
        registry.put(GreetingStrategyType.STANDARD, StandardGreetingStrategy::new);
        registry.put(GreetingStrategyType.VERBOSE, VerboseGreetingStrategy::new);
    }

    @Override
    public GreetingStrategy getStrategy(GreetingStrategyType type) {
        return registry.get(type).get();
    }
}

interface GreetingFormatter {
    String format(String rawGreeting, PolitenessLevel level);
}

class DefaultGreetingFormatter implements GreetingFormatter {
    @Override
    public String format(String rawGreeting, PolitenessLevel level) {
        if (level == PolitenessLevel.FORMAL) {
            return rawGreeting.replace("!", ".");
        }
        return rawGreeting;
    }
}

class GreetingFacade {
    private final GreetingStrategyProvider strategyProvider;
    private final GreetingFormatter formatter;
    private final GreetingStrategyType type;

    GreetingFacade(GreetingStrategyProvider strategyProvider, GreetingFormatter formatter, GreetingStrategyType type) {
        this.strategyProvider = strategyProvider;
        this.formatter = formatter;
        this.type = type;
    }

    public String generate(GreetingContext context) {
        GreetingStrategy strategy = strategyProvider.getStrategy(type);
        String raw = strategy.buildGreeting(context);
        return formatter.format(raw, context.getRequest().getPolitenessLevel());
    }
}

class GreetingFacadeFactory {
    private static final GreetingFacadeFactory INSTANCE = new GreetingFacadeFactory();

    private GreetingFacadeFactory() {}

    public static GreetingFacadeFactory getInstance() {
        return INSTANCE;
    }

    public GreetingFacade createFacade(GreetingStrategyType type) {
        GreetingStrategyProvider provider = new GreetingStrategyProviderImpl();
        GreetingFormatter formatter = new DefaultGreetingFormatter();
        return new GreetingFacade(provider, formatter, type);
    }
}
