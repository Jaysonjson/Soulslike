package json.jayson.common.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.SharedSuggestionProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ScreenArgument implements ArgumentType<ScreenArgument.IScreenArgument> {
    @Override
    public IScreenArgument parse(StringReader reader) throws CommandSyntaxException {
        String text = reader.getRemaining();
        reader.setCursor(reader.getTotalLength());
        return () -> text;
    }

    public static ScreenArgument id() {
        return new ScreenArgument();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("level_up");
        return SharedSuggestionProvider.suggest(suggestions, builder);
    }

    public interface IScreenArgument {

        String screenId();

    }

}
