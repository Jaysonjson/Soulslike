package json.jayson.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import json.jayson.common.commands.arguments.ScreenArgument;
import net.minecraft.server.commands.GiveCommand;

public class SoulsLikeCommand {

    public static void register(CommandDispatcher<CommandSourceStack> p_214446_, CommandBuildContext p_214447_) {
        p_214446_.register(Commands.literal("soulslike").requires((p_137777_) -> {
            return p_137777_.hasPermission(2);
        }).then(Commands.argument("screens", ScreenArgument.id()).executes((p_137784_) -> {
            return openScren(p_137784_.getSource(), p_137784_);
        })).then(Commands.argument("targets", EntityArgument.players()).executes(context -> {
            return 0;
        })));
    }

    private static int openScren(CommandSourceStack p_137779_, CommandContext<CommandSourceStack> p_214446_) throws CommandSyntaxException {
        String id = p_214446_.getArgument("screens", String.class);
        p_137779_.sendSuccess(() -> {
            return Component.literal("TEST: " + id);
        }, true);
        return 0;
    }

}
