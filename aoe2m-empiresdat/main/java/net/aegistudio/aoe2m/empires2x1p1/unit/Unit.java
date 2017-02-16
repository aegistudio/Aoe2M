package net.aegistudio.aoe2m.empires2x1p1.unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.empires2x1p1.Resource;
import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;
import net.aegistudo.aoe2m.unittype.EnumUnitType;
import static net.aegistudo.aoe2m.unittype.EnumUnitType.*;

import net.aegistudo.aoe2m.unittype.SlotUnitBuilder;

public class Unit {
	private static final EnumUnitType[] TYPING = new EnumUnitType[] {
			null, EYE_CANDY, FLAG, DEAD_OR_FISH, BIRD, BIRD,
			PROJECTILE, LIVING, BUILDING, TREE
	};
	
	// Header data set.
	public final Wrapper<Byte> type = Container.byte0();
	public EnumUnitType getActualType() {
		assert type.get() >= 10;
		assert type.get() <= 90;
		
		return TYPING[(type.get() / 10)];
	}
	
	public final Wrapper<Short> id0 = Container.short0();
	
	public final Wrapper<Short> dllName = Container.short0();
	
	public final Wrapper<Short> dllCreation = Container.short0();
	
	public final Wrapper<Short> unitClass = Container.short0();
	
	public final Wrapper<Short> graphicsStanding0 = Container.short0();
	
	public final Wrapper<Short> graphicsStanding1 = Container.short0();
	
	public final Wrapper<Short> graphicsDying0 = Container.short0();
	
	public final Wrapper<Short> graphicsDying1 = Container.short0();
	
	public final Wrapper<Byte> deathMode = Container.byte0();
	
	public final Wrapper<Short> hitPoints = Container.short0();
	
	public final Wrapper<Float> eyeSight = Container.float0();
	
	public final Wrapper<Byte> garrisonCapacity = Container.byte0();
	
	public final Wrapper<Float> aabbX = Container.float0();
	
	public final Wrapper<Float> aabbY = Container.float0();
	
	public final Wrapper<Float> aabbZ = Container.float0();
	
	public final Wrapper<Short> soundCreation0 = Container.short0();
	
	public final Wrapper<Short> soundCreation1 = Container.short0();
	
	public final Wrapper<Short> deadUnit = Container.short0();
	
	public final Wrapper<Byte> placementMode = Container.byte0();
	
	public final Wrapper<Byte> airMode = Container.byte0();
	
	public final Wrapper<Short> icon = Container.short0();
	
	public final Wrapper<Byte> hidden = Container.byte0();

	public final Wrapper<Short> uk0 = Container.short0();
	
	public final Wrapper<Short> enabled = Container.short0();
	
	public final Wrapper<Short> placementSideTerrain0 = Container.short0();
	
	public final Wrapper<Short> placementSideTerrain1 = Container.short0();
	
	public final Wrapper<Short> placementTerrain0 = Container.short0();
	
	public final Wrapper<Short> placementTerrain1 = Container.short0();
	
	public final Wrapper<Float> clearanceX = Container.float0();
	
	public final Wrapper<Float> clearanceY = Container.float0();
	
	public final Wrapper<Byte> buildingMode = Container.byte0();

	public final Wrapper<Byte> fogVisibility = Container.byte0();
	
	public final Wrapper<Short> restriction = Container.short0();
	
	public final Wrapper<Byte> flyMode = Container.byte0();
	
	public final Wrapper<Short> resourceCapacity = Container.short0();
	
	public final Wrapper<Float> resourceDecay = Container.float0();
	
	public final Wrapper<Byte> blastDefense = Container.byte0();
	
	public final Wrapper<Byte> subType = Container.byte0();
	
	public final Wrapper<Byte> interactionMode = Container.byte0();
	
	public final Wrapper<Byte> minimapMode = Container.byte0();
	
	public final Wrapper<Short> commandAttribute = Container.short0();
	
	public final Wrapper<Float> uk1 = Container.float0();
	
	public final Wrapper<Byte> minimapColor = Container.byte0();
	
	public final Wrapper<Short> dllHelp = Container.short0();
	
	public final Wrapper<Short> hotKey0 = Container.short0();
	
	public final Wrapper<Short> hotKey1 = Container.short0();
	
	public final Wrapper<Short> hotKey2 = Container.short0();
	
	public final Wrapper<Short> hotKey3 = Container.short0();
	
	public final Wrapper<Byte> uk2 = Container.byte0();
	
	public final Wrapper<Byte> uk3 = Container.byte0();
	
	public final Wrapper<Byte> unselectable = Container.byte0();
	
	public final Wrapper<Byte> uk4 = Container.byte0();
	
	public final Wrapper<Byte> uk5 = Container.byte0();
	
	public final Wrapper<Byte> uk6 = Container.byte0();
	
	public final Wrapper<Byte> selectionMask = Container.byte0();
	
	public final Wrapper<Byte> selectionShapeType = Container.byte0();
	
	public final Wrapper<Byte> selectionShape = Container.byte0();
	
	public final Wrapper<Byte> attribute = Container.byte0();
	
	public final Wrapper<Byte> civilization = Container.byte0();
	
	public final Wrapper<Short> attributePiece = Container.short0();

	public final Wrapper<Byte> selectionEffect = Container.byte0();
	
	public final Wrapper<Byte> selectionColorEditor = Container.byte0();
	
	public final Wrapper<Float> selectionShapeX = Container.float0();
	
	public final Wrapper<Float> selectionShapeY = Container.float0();
	
	public final Wrapper<Float> selectionShapeZ = Container.float0();
	
	public List<Resource> resourceStorage = new ArrayList<>();
	
	public List<DamageGraphics> damageGraphics = new ArrayList<>();

	public final Wrapper<Short> soundSelection = Container.short0();
	
	public final Wrapper<Short> soundDying = Container.short0();
	
	public final Wrapper<Byte> attackMode = Container.byte0();
	
	public final Wrapper<Byte> uk7 = Container.byte0();
	
	public final Wrapper<String> name = Container.string0();
	
	public final Wrapper<Short> id1 = Container.short0();
	
	public final Wrapper<Short> id2 = Container.short0();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.signed8(type);
		
		Wrapper<Integer> nameLength = new Container<>(
				name.get().getBytes().length);
		translator.unsigned16(nameLength);
		
		translator.signed16(id0);
		translator.signed16(dllName);
		translator.signed16(dllCreation);
		
		translator.signed16(unitClass);
		translator.signed16(graphicsStanding0);
		translator.signed16(graphicsStanding1);
		translator.signed16(graphicsDying0);
		translator.signed16(graphicsDying1);
		
		translator.signed8(deathMode);
		translator.signed16(hitPoints);
		translator.float32(eyeSight);
		translator.signed8(garrisonCapacity);
		
		translator.float32(aabbX);
		translator.float32(aabbY);
		translator.float32(aabbZ);
		
		translator.signed16(soundCreation0);
		translator.signed16(soundCreation1);
		translator.signed16(deadUnit);
		
		translator.signed8(placementMode);
		translator.signed8(airMode);
		
		translator.signed16(icon);
		translator.signed8(hidden);
		
		translator.signed16(uk0);
		translator.signed16(enabled);
		
		translator.signed16(placementSideTerrain0);
		translator.signed16(placementSideTerrain1);
		translator.signed16(placementTerrain0);
		translator.signed16(placementTerrain1);
		
		translator.float32(clearanceX);
		translator.float32(clearanceY);
		
		translator.signed8(buildingMode);
		translator.signed8(fogVisibility);
		
		translator.signed16(restriction);
		translator.signed8(flyMode);
		translator.signed16(resourceCapacity);
		
		translator.float32(resourceDecay);
		translator.signed8(blastDefense);
		translator.signed8(subType);
		translator.signed8(interactionMode);
		translator.signed8(minimapMode);
		
		translator.signed16(commandAttribute);
		translator.float32(uk1);
		translator.signed8(minimapColor);
		translator.signed16(dllHelp);
		
		translator.signed16(hotKey0);
		translator.signed16(hotKey1);
		translator.signed16(hotKey2);
		translator.signed16(hotKey3);
		
		translator.signed8(uk2);
		translator.signed8(uk3);
		translator.signed8(unselectable);
		translator.signed8(uk4);
		translator.signed8(uk5);
		//translator.signed8(uk6);
		translator.signed8(selectionMask);
		translator.signed8(selectionShapeType);
		translator.signed8(selectionShape);
		
		translator.signed8(attribute);
		translator.signed8(civilization);
		translator.signed16(attributePiece);
		
		translator.signed8(selectionEffect);
		translator.signed8(selectionColorEditor);
		translator.float32(selectionShapeX);
		translator.float32(selectionShapeY);
		translator.float32(selectionShapeZ);
		
		translator.array(3, resourceStorage, Resource::new, 
				Resource::translateStorage);
		
		Wrapper<Byte> damageGraphicsCount = new Container<>(
				(byte)damageGraphics.size());
		translator.signed8(damageGraphicsCount);
		translator.array(damageGraphicsCount.get(), damageGraphics, 
				DamageGraphics::new, DamageGraphics::translate);
		
		translator.signed16(soundSelection);
		translator.signed16(soundDying);
		translator.signed8(attackMode);
		translator.signed8(uk7);
		
		translator.string(nameLength.get(), name);
		translator.signed16(id1);
		translator.signed16(id2);
		
		try {
			EnumUnitType actualType = getActualType();
			actualType.build(new SlotUnitBuilder<Exception>() {{
				flagSpeed = () -> translateFlagSpeed(translator);
				walking = () -> translateWalking(translator);
				discover = () -> translateDiscover(translator);
				combat = () -> translateCombat(translator);
				projectile = () -> translateProjectile(translator);
				production = () -> translateProduction(translator);
				building = () -> translateBuilding(translator);
			}});
		}
		catch(Exception e) {
			if(e instanceof IOException) 
				throw (IOException)e;
			if(e instanceof CorruptException)
				throw (CorruptException)e;
			throw new AssertionError();
		}
	}
	
	public final Wrapper<Float> speed = Container.float0();
	
	public void translateFlagSpeed(Translator translator) throws IOException {
		translator.float32(speed);
	}
	
	// Walking data set.
	public final Wrapper<Short> graphicsWalking0 = Container.short0();
	public final Wrapper<Short> graphicsWalking1 = Container.short0();
	public final Wrapper<Float> rotationSpeed = Container.float0();
	public final Wrapper<Byte> uk8 = Container.byte0();
	public final Wrapper<Short> trackingUnit = Container.short0();
	public final Wrapper<Byte> trackingUnitUsed = Container.byte0();
	public final Wrapper<Float> trackingUnitDensity = Container.float0();
	public final Wrapper<Float> uk9 = Container.float0();
	public final Wrapper<Short> uk10 = Container.short0();
	public final List<Wrapper<Float>> rotations = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translateWalking(Translator translator) throws IOException, CorruptException {
		translator.signed16(graphicsWalking0);
		translator.signed16(graphicsWalking1);

		translator.float32(rotationSpeed);
		translator.signed8(uk8);
		translator.signed16(trackingUnit);
		//translator.signed8(trackingUnitUsed);
		translator.float32(trackingUnitDensity);
		//translator.float32(uk9);
		translator.signed16(uk10);
		translator.array(5, rotations, Container::float0, 
				Translator.reverse(Translator::float32));
	}
	
	// Discover data set.
	public final Wrapper<Short> discoverAction = Container.short0();
	
	public final Wrapper<Float> searchRadius = Container.float0();
	
	public final Wrapper<Float> workRate = Container.float0();
	
	public final Wrapper<Short> dropSite0 = Container.short0();
	
	public final Wrapper<Short> dropSite1 = Container.short0();
	
	public final Wrapper<Byte> takeSwapGroup = Container.byte0();
	
	public final Wrapper<Short> moveSound = Container.short0();
	
	public final Wrapper<Short> stopSound = Container.short0();
	
	public final Wrapper<Byte> animalMode = Container.byte0();
	
	public void translateDiscover(Translator translator) throws IOException {
		translator.signed16(discoverAction);
		translator.float32(searchRadius);
		translator.float32(workRate);
		translator.signed16(dropSite0);
		translator.signed16(dropSite1);
		translator.signed8(takeSwapGroup);
		translator.signed16(moveSound);
		translator.signed16(stopSound);
		translator.signed8(animalMode);
	}
	
	// Combat data set.
	public final Wrapper<Short> defaultArmor = Container.short0();
	
	public static class Damage {
		public final Wrapper<Short> type = Container.short0();
		
		public final Wrapper<Short> amount = Container.short0();
		
		public void translate(Translator translator) throws IOException {
			translator.signed16(type);
			translator.signed16(amount);
		}
	}
	
	public final List<Damage> attack = new ArrayList<>();
	
	public final List<Damage> armor = new ArrayList<>();
	
	public final Wrapper<Short> interactionType = Container.short0();
	
	public final Wrapper<Float> maxRange = Container.float0();
	
	public final Wrapper<Float> blastWidth = Container.float0();
	
	public final Wrapper<Float> reloadTime = Container.float0();
	
	public final Wrapper<Short> projectileUnit = Container.short0();
	
	public final Wrapper<Short> accuracyPercent = Container.short0();
	
	public final Wrapper<Byte> towerMode = Container.byte0();
	
	public final Wrapper<Short> delay = Container.short0();
	
	public final Wrapper<Float> displacementLr = Container.float0();
	
	public final Wrapper<Float> displacementDistance = Container.float0();
	
	public final Wrapper<Float> displacementHeight = Container.float0();
	
	public final Wrapper<Byte> blastAttack = Container.byte0();
	
	public final Wrapper<Float> minRange = Container.float0();
	
	public final Wrapper<Float> accuracyDispersion = Container.float0();
	
	public final Wrapper<Short> graphicsAttack = Container.short0();
	
	public final Wrapper<Short> displayedMeleeArmor = Container.short0();
	
	public final Wrapper<Short> displayedAttack = Container.short0();
	
	public final Wrapper<Float> displayedRange = Container.float0();
	
	public final Wrapper<Float> displayedReloadTime = Container.float0();
	
	@SuppressWarnings("unchecked")
	public void translateCombat(Translator translator) throws IOException, CorruptException {
		translator.signed16(defaultArmor);
		
		Wrapper<Integer> attackCount = new Container<>(attack.size());
		translator.unsigned16(attackCount);
		translator.array(attackCount.get(), attack, 
				Damage::new, Damage::translate);
		
		Wrapper<Integer> armorCount = new Container<>(armor.size());
		translator.unsigned16(armorCount);
		translator.array(armorCount.get(), armor, 
				Damage::new, Damage::translate);
		
		translator.signed16(interactionType);
		translator.float32(maxRange);
		translator.float32(blastWidth);
		translator.float32(reloadTime);
		translator.signed16(projectileUnit);
		translator.signed16(accuracyPercent);
		translator.signed8(towerMode);
		translator.signed16(delay);
		
		translator.float32(displacementLr);
		translator.float32(displacementDistance);
		translator.float32(displacementHeight);
		
		translator.signed8(blastAttack);
		translator.float32(minRange);
		translator.float32(accuracyDispersion);
		
		translator.signed16(graphicsAttack);
		translator.signed16(displayedMeleeArmor);
		translator.signed16(displayedAttack);
		translator.float32(displayedRange);
		translator.float32(displayedReloadTime);
	}
	
	// Projectile data set.
	public final Wrapper<Byte> stretch = Container.byte0();
	
	public final Wrapper<Byte> smart = Container.byte0();
	
	public final Wrapper<Byte> dropAnimation = Container.byte0();
	
	public final Wrapper<Byte> penetration = Container.byte0();
	
	public final Wrapper<Byte> uk11 = Container.byte0();
	
	public final Wrapper<Float> projectileArc = Container.float0();
	
	public void translateProjectile(Translator translator) throws IOException {
		translator.signed8(stretch);
		translator.signed8(smart);
		translator.signed8(dropAnimation);
		translator.signed8(penetration);
		translator.signed8(uk11);
		translator.float32(projectileArc);
	}
	
	// Production data set.
	public final List<Resource> cost = new ArrayList<>();
	
	public final Wrapper<Short> creationTime = Container.short0();
	
	public final Wrapper<Short> creationLocation = Container.short0();
	
	public final Wrapper<Byte> creationButton = Container.byte0();
	
	public final Wrapper<Float> uk12 = Container.float0();
	
	public final Wrapper<Float> uk13 = Container.float0();
	
	public final Wrapper<Byte> creatableType = Container.byte0();
	
	public final Wrapper<Byte> heroMode = Container.byte0();
	
	public final Wrapper<Integer> graphicsGarrison = Container.int0();
	
	public static class AttackProjectile {
		public final Wrapper<Float> count = Container.float0();
		
		public final Wrapper<Byte> max = Container.byte0();	
		
		public final Wrapper<Float> areaWidth = Container.float0();
		
		public final Wrapper<Float> areaHeight = Container.float0();
		
		public final Wrapper<Float> randomness = Container.float0();
		
		public final Wrapper<Integer> secondaryUnit = Container.int0();
		
		public void translate(Translator translator) throws IOException, CorruptException {
			translator.float32(count);
			translator.signed8(max);
			translator.float32(areaWidth);
			translator.float32(areaHeight);
			translator.float32(randomness);
			translator.signed32(secondaryUnit);
		}
	}
	
	public final AttackProjectile attackProjectile = new AttackProjectile();
	
	public final Wrapper<Integer> graphicsSpecial = Container.int0();
	
	public final Wrapper<Byte> specialActivation = Container.byte0();
	
	public final Wrapper<Short> displayedPierceArmor = Container.short0();
	
	@SuppressWarnings("unchecked")
	public void translateProduction(Translator translator) throws IOException, CorruptException {
		translator.array(3, cost, Resource::new, Resource::translateCost);
		translator.signed16(creationTime);
		translator.signed16(creationLocation);
		translator.signed8(creationButton);
		
		translator.float32(uk12);
		translator.float32(uk13);
		
		translator.signed8(creatableType);
		translator.signed8(heroMode);
		translator.signed32(graphicsGarrison);
		
		attackProjectile.translate(translator);
		
		translator.signed32(graphicsSpecial);
		translator.signed8(specialActivation);
		translator.signed16(displayedPierceArmor);
	}
	
	// Building data set.
	public final Wrapper<Short> graphicsConstruction = Container.short0();
	
	public final Wrapper<Short> graphicsSnow = Container.short0();	
	
	public final Wrapper<Short> adjacenceMode = Container.short0();
	
	public final Wrapper<Short> iconDisabler = Container.short0();
	
	public final Wrapper<Byte> disappersWhenBuilt = Container.byte0();
	
	public final Wrapper<Short> stackUnit = Container.short0();
	
	public final Wrapper<Short> terrain = Container.short0();
	
	public final Wrapper<Short> resource = Container.short0();
	
	public final Wrapper<Short> research = Container.short0();
	
	public final Wrapper<Byte> uk14 = Container.byte0();
	
	public static class BuildingAnnex {
		public final Wrapper<Short> unit = new Container<>((short)-1);
		
		public final Wrapper<Float> misplaced0 = Container.float0();
		
		public final Wrapper<Float> misplaced1 = Container.float0();
		
		public void translate(Translator translator) throws IOException {
			translator.signed16(unit);
			translator.float32(misplaced0);
			translator.float32(misplaced1);
		}
		
		public boolean invalid() {
			return unit.get() < 0;
		}
	}
	
	public final List<BuildingAnnex> buildingAnnex = new ArrayList<>();
	
	public final Wrapper<Short> headUnit = Container.short0();
	
	public final Wrapper<Short> transformUnit = Container.short0();
	
	public final Wrapper<Short> uk15 = Container.short0();
	
	public final Wrapper<Short> soundConstruction = Container.short0();
	
	public final Wrapper<Byte> garrisonType = Container.byte0();
	
	public final Wrapper<Float> garrisonHealRate = Container.float0();
	
	public final Wrapper<Integer> uk16 = Container.int0();
	
	public final Wrapper<Integer> uk17 = Container.int0();

	public final Wrapper<Short> uk18 = Container.short0();
	
	public final Wrapper<Short> uk19 = Container.short0();
	
	public final Wrapper<Integer> uk20 = Container.int0();
	
	@SuppressWarnings("unchecked")
	public void translateBuilding(Translator translator) throws IOException, CorruptException {
		translator.signed16(graphicsConstruction);
		translator.signed16(graphicsSnow);
		translator.signed16(adjacenceMode);
		translator.signed16(iconDisabler);
		translator.signed16(stackUnit);
		translator.signed16(terrain);
		translator.signed16(resource);
		translator.signed16(research);
		translator.signed8(disappersWhenBuilt);
		//translator.signed8(uk14);
		translator.array(4, buildingAnnex, BuildingAnnex::new, BuildingAnnex::translate);
		buildingAnnex.removeIf(BuildingAnnex::invalid);
		
		translator.signed16(headUnit);
		translator.signed16(transformUnit);
		translator.signed16(uk15);
		translator.signed16(soundConstruction);
		translator.signed8(garrisonType);
		translator.float32(garrisonHealRate);
		
		translator.signed32(uk16);
		//translator.signed32(uk17);
		translator.signed16(uk18);
		translator.signed16(uk19);
		translator.signed32(uk20);
	}
}
