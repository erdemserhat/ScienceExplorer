package com.erdemserhat.scienceexplorer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

object SampleData {
    //Scientists Static Data
    val scientistList = listOf(
        R.drawable.aege_bohr to R.string.bohr,
        R.drawable.arsiment to R.string.archimedes,
        R.drawable.aziz_sancar to R.string.aziz_sancar,
        R.drawable.edison to R.string.thomas_edison,
        R.drawable.einstein to R.string.albert_einstein,
        R.drawable.euler to R.string.euler,
        R.drawable.galelio to R.string.galileo,
        R.drawable.ibni_sina to R.string.ibn_sina,
        R.drawable.marie_curie to R.string.marie_curie,
        R.drawable.newton to R.string.isaac_newton,
        R.drawable.oktay_sinanoglu to R.string.oktay_sinanoglu,
        R.drawable.pasteur to R.string.pasteur,
        R.drawable.stephin to R.string.stephen_hawking,
        R.drawable.tesla to R.string.nikola_tesla
    ).map { DrawableStringPair(it.first,it.second) }

    data class DrawableStringPair(
        @DrawableRes val drawable:Int,
        @StringRes val text:Int
    )

    //Information Cards Data

    val informationCardsList = listOf(
        R.drawable.artificial_intelligence_ml to R.string.artificial_intelligence_ml,
        R.drawable.big_bang_theory to R.string.big_bang_theory,
        R.drawable.biological_clocks_nature to R.string.biological_clocks_nature,
        R.drawable.discovery_dna to R.string.discovery_dna,
        R.drawable.electricity_magnetism to R.string.electricity_magnetism,
        R.drawable.expansion_rate_universe to R.string.expansion_rate_universe,
        R.drawable.higgs_boson_mass to R.string.higgs_boson_mass,
        R.drawable.human_brain_unexplored to R.string.human_brain_unexplored,
        R.drawable.infinite_digits_pi to R.string.infinite_digits_pi,
        R.drawable.mysteries_quantum_physics to R.string.mysteries_quantum_physics,
        R.drawable.neurons_nervous_system to R.string.neurons_nervous_system,
        R.drawable.particle_physics to R.string.particle_physics,
        R.drawable.potential_quantum_computers to R.string.potential_quantum_computers,
        R.drawable.schwarzschild_black_holes to R.string.schwarzschild_black_holes,
        R.drawable.theory_relativity_equation to R.string.theory_relativity_equation

    ).map { DrawableStringPair(it.first, it.second) }


}