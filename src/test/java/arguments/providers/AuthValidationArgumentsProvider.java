package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class AuthValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new AuthValidationArgumentsHolder(
                        Collections.emptyMap()
                ),
                new AuthValidationArgumentsHolder(
                        Map.of("key", "6dfe2176e5f391f8414d73603b6a9f77")
//                ),
//                new AuthValidationArgumentsHolder(
//                        Map.of("token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BF")
                )
        ).map(Arguments::of);
    }
}
