package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class CapletChain {
    public static void init() {

        // Cyanide
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(CAPLET_BODY.getStackForm(1))
                .inputs(SodiumCyanideSmall.getItemStack())
                .inputs(CAPLET_CAP.getStackForm(1))
                .outputs(CYANIDE_CAPLET.getStackForm(1))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .outputs(SodiumCyanide.getItemStack(3))
                .fluidOutputs(Water.getFluid(1000))
                .duration(120).EUt(VA[LV]).buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .notConsumable(dust, Platinum)
                .fluidOutputs(HydrogenCyanide.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .duration(120).EUt(VA[MV]).buildAndRegister();
        ModHandler.addShapelessRecipe("sodium_cyanide_small", SodiumCyanideSmall.getItemStack(4), SodiumCyanide.getItemStack());

        // Paracetamol
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(500)
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(Phenol.getFluid(5000))
                .fluidOutputs(Nitrophenols.getFluid(6000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder().EUt(480).duration(10)
                .fluidInputs(Nitrophenols.getFluid(100))
                .outputs(IVNitrophenol.getItemStack(1))
                .outputs(IINitrophenol.getItemStack(1))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(80)
                .inputs(IVNitrophenol.getItemStack(10))
                .fluidInputs(Hydrogen.getFluid(6000))
                .notConsumable(dust, Nickel)
                .fluidOutputs(Water.getFluid(2000))
                .outputs(Aminophenol.getItemStack(9))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(500)
                .notConsumable(new IntCircuitIngredient(16))
                .fluidInputs(MethylAcetate.getFluid(1000))
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .fluidOutputs(AceticAnhydride.getFluid(2000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(1800).duration(400)
                .notConsumable(new IntCircuitIngredient(16))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .inputs(Aminophenol.getItemStack(3))
                .outputs(Paracetamol.getItemStack(9))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(20)
                .inputs(CAPLET_BODY.getStackForm())
                .inputs(ParacetamolSmall.getItemStack())
                .inputs(CAPLET_CAP.getStackForm())
                .outputs(PARACETAMOL_CAPLET.getStackForm())
                .buildAndRegister();
        ModHandler.addShapelessRecipe("paracetamol_small", ParacetamolSmall.getItemStack(4), Paracetamol.getItemStack());
        ModHandler.addShapelessRecipe("handful_paracetamol", HANDFUL_PARACETAMOL.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm());

        // Plutonium 241
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(20)
                .inputs(CAPLET_BODY.getStackForm())
                .input(dustSmall, Plutonium241)
                .inputs(CAPLET_CAP.getStackForm())
                .outputs(PLUTONIUM_241_CAPLET.getStackForm())
                .buildAndRegister();

        //N-methyl-1-phenylpropan-2-amine
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(BenzylChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .inputs(SodiumCyanide.getItemStack(3))
                .fluidInputs(BenzylChloride.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(BenzylCyanide.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .fluidInputs(BenzylCyanide.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Water.getFluid(2000))
                .outputs(PhenylaceticAcid.getItemStack(18))
                .output(dust, AmmoniumChloride, 2)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder().EUt(480).duration(400)
                .input(dust, Thorium)
                .fluidInputs(Oxygen.getFluid(2000))
                .outputs(ThoriumDioxide.getItemStack(3))
                .blastFurnaceTemp(1200)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .inputs(PhenylaceticAcid.getItemStack(18))
                .notConsumable(ThoriumDioxide.getItemStack())
                .fluidInputs(AceticAcid.getFluid(1000))
                .outputs(Phenylacetone.getItemStack(20))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .input(dust, Sodium)
                .fluidInputs(Methanol.getFluid(1000))
                .outputs(SodiumMethoxide.getItemStack(6))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .notConsumable(SodiumMethoxide.getItemStack())
                .fluidOutputs(MethylFormate.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .fluidOutputs(Methylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .fluidInputs(MethylFormate.getFluid(1000))
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidOutputs(Methylformamide.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .inputs(Phenylacetone.getItemStack(20))
                .fluidInputs(Methylformamide.getFluid(2000))
                .outputs(FormylImpostramine.getItemStack(28))
                .fluidOutputs(Methylamine.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .inputs(FormylImpostramine.getItemStack(28))
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(ImpostramineHydrochloride.getItemStack(2))
                .fluidOutputs(FormicAcid.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(400)
                .fluidInputs(FormicAcid.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .notConsumable(SulfuricAcid.getFluid(10))
                .fluidOutputs(MethylFormate.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(20)
                .inputs(CAPLET_BODY.getStackForm())
                .inputs(ImpostramineHydrochlorideSmall.getItemStack())
                .inputs(CAPLET_CAP.getStackForm())
                .outputs(IMPOSTRAMINE_CAPLET.getStackForm())
                .buildAndRegister();

        ModHandler.addShapelessRecipe("impostramine_hydrochloride_small", ImpostramineHydrochlorideSmall.getItemStack(4), ImpostramineHydrochloride.getItemStack());
        ModHandler.addShapelessRecipe("impostramine_hydrochloride", ImpostramineHydrochloride.getItemStack(), ImpostramineHydrochlorideSmall.getItemStack(4));



    }
}