package com.lk.mancala.game;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MancalaFixture {

  public static Map<Integer, Pit> thereIsPlayeBoard(Integer ...stones) {
    AtomicInteger index = new AtomicInteger(1);
    return stream(stones).map(Pit::new)
        .collect(toMap(o -> index.getAndIncrement(), o -> o));
  }
}
