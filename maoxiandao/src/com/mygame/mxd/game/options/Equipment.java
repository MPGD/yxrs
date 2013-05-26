package com.mygame.mxd.game.options;

public class Equipment {

	public static final String LEVEL = "level";
	public static final String ITEM = "item";
	public static final String TYPE = "type";
	public static final String PATH = "path";
	public static final String DESCRIPTION = "description";
	public static final String PROBABILITY = "probability";
	public static final String DEFENSE = "defense";
	public static final String LUCKY = "lucky";
	public static final String NAME = "name";
	public static final String EFFECT = "effect";
	public static final String VALUE = "value";

	public Level level = new Level();
	public Level.Name name = level.new Name();
	public Level.Name.Effect effect = name.new Effect();

	class Level {
		public int levelValue;

		class Name {

			public String itemName;
			public String type;
			public String path;
			public String description;
			public float probability;

			class Effect {
				public int defense;
				public int lucky;
			}
		}
	}
}
