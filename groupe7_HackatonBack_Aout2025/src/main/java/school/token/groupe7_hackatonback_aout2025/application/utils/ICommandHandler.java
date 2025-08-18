package school.token.groupe7_hackatonback_aout2025.application.utils;

public interface ICommandHandler<I, O> {
    O handle(I input);
}
