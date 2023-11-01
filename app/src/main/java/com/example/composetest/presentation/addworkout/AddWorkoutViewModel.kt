package com.example.composetest.presentation.addworkout
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.data.mapToEntity
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.domain.FieldGoalDataEntity
import com.example.composetest.presentation.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _state = mutableStateOf(AddExercisesState())
    val state : State<AddExercisesState> = _state
    fun onEvent(event: AddExerciseEvent) {
        when (event) {
            is AddExerciseEvent.AddExercise -> {
                val newList: MutableList<Exercise> = state.value.exercises.toMutableList()
                newList.add(event.exercise)
                _state.value = state.value.copy(
                    exercises = newList.toList()
                )
            }
            is AddExerciseEvent.DeleteExercise -> {
                val newList: MutableList<Exercise> = state.value.exercises.toMutableList()
                newList.remove(event.exercise)
                _state.value = state.value.copy(
                    exercises = newList.toList()
                )
            }
            is AddExerciseEvent.ShowSaveButton -> {
                _state.value = state.value.copy(
                    showSaveButton = event.showSaveButton
                )
            }
            is AddExerciseEvent.SaveExercises -> {
                CoroutineScope(Dispatchers.IO).launch {
                    exerciseRepository.insertExercise(convertToEntities(state.value.exercises))
                    val currFieldGoalData: FieldGoalDataEntity? = exerciseRepository.fetchFieldGoalData()
                    if (currFieldGoalData != null) {
                        exerciseRepository.insertFieldGoalData(
                            addFieldGoalData(
                                getNumberOfFieldGoals(state.value.exercises),
                                currFieldGoalData
                            )
                        )
                    } else {
                        exerciseRepository.insertFieldGoalData(getNumberOfFieldGoals(state.value.exercises))
                    }
                }
            }
        }
    }

    private fun getNumberOfFieldGoals(list: List<Exercise>): FieldGoalDataEntity {
        val returnData = FieldGoalDataEntity(
            totalFieldGoals = 0,
            totalFieldGoalsMade = 0,
            closeRangeFieldGoals = 0,
            closeRangeFieldGoalsMade = 0,
            midRangeFieldGoals = 0,
            midRangeFieldGoalsMade = 0,
            threePointFieldGoals = 0,
            threePointFieldGoalsMade = 0,
            baseLineFieldGoals = 0,
            baseLineFieldGoalsMade = 0,
            elbowFieldGoals = 0,
            elbowFieldGoalsMade = 0,
            diagonalFieldGoals = 0,
            diagonalFieldGoalsMade = 0,
            centerFieldGoals = 0,
            centerFieldGoalsMade = 0,
            leftSideFieldGoals = 0,
            leftSideFieldGoalsMade = 0,
            rightSideFieldGoals = 0,
            rightSideFieldGoalsMade = 0
        )

        for (exercise in list) {
            returnData.totalFieldGoals += exercise.totalShots
            returnData.totalFieldGoalsMade += exercise.shotsMade
            when (exercise.side) {
                "Right" -> {
                    returnData.rightSideFieldGoals += exercise.totalShots
                    returnData.rightSideFieldGoalsMade += exercise.shotsMade
                }
                "Left" -> {
                    returnData.leftSideFieldGoals += exercise.totalShots
                    returnData.leftSideFieldGoalsMade += exercise.shotsMade
                }
            }
            when (exercise.range) {
                "Close Range" -> {
                    returnData.closeRangeFieldGoals += exercise.totalShots
                    returnData.closeRangeFieldGoalsMade += exercise.shotsMade
                }
                "Mid Range" -> {
                    returnData.midRangeFieldGoals += exercise.totalShots
                    returnData.midRangeFieldGoalsMade += exercise.shotsMade
                }
                "Three Pointer" -> {
                    returnData.threePointFieldGoals += exercise.totalShots
                    returnData.threePointFieldGoalsMade += exercise.shotsMade
                }
            }
            when (exercise.location) {
                "Baseline" -> {
                    returnData.baseLineFieldGoals += exercise.totalShots
                    returnData.baseLineFieldGoalsMade += exercise.shotsMade
                }
                "Diagonal" -> {
                    returnData.diagonalFieldGoals += exercise.totalShots
                    returnData.diagonalFieldGoalsMade += exercise.shotsMade
                }
                "Elbow" -> {
                    returnData.elbowFieldGoals += exercise.totalShots
                    returnData.elbowFieldGoalsMade += exercise.shotsMade
                }
                "Center" -> {
                    returnData.centerFieldGoals += exercise.totalShots
                    returnData.centerFieldGoalsMade += exercise.shotsMade
                }
            }
        }

        return returnData
    }

    private fun addFieldGoalData(newData: FieldGoalDataEntity, oldData: FieldGoalDataEntity): FieldGoalDataEntity {
        return FieldGoalDataEntity(
            totalFieldGoals = newData.totalFieldGoals + oldData.totalFieldGoals,
            totalFieldGoalsMade = newData.totalFieldGoalsMade + oldData.totalFieldGoalsMade,
            closeRangeFieldGoals = newData.closeRangeFieldGoals + oldData.closeRangeFieldGoals,
            closeRangeFieldGoalsMade = newData.closeRangeFieldGoalsMade + oldData.closeRangeFieldGoalsMade,
            midRangeFieldGoals = newData.midRangeFieldGoals + oldData.midRangeFieldGoals,
            midRangeFieldGoalsMade = newData.midRangeFieldGoalsMade + oldData.midRangeFieldGoalsMade,
            threePointFieldGoals = newData.threePointFieldGoals + oldData.threePointFieldGoals,
            threePointFieldGoalsMade = newData.threePointFieldGoalsMade + oldData.threePointFieldGoalsMade,
            baseLineFieldGoals = newData.baseLineFieldGoals + oldData.baseLineFieldGoals,
            baseLineFieldGoalsMade = newData.baseLineFieldGoalsMade + oldData.baseLineFieldGoalsMade,
            elbowFieldGoals = newData.elbowFieldGoals + oldData.elbowFieldGoals,
            elbowFieldGoalsMade = newData.elbowFieldGoalsMade + oldData.elbowFieldGoalsMade,
            diagonalFieldGoals = newData.diagonalFieldGoals + oldData.diagonalFieldGoals,
            diagonalFieldGoalsMade = newData.diagonalFieldGoalsMade + oldData.diagonalFieldGoalsMade,
            centerFieldGoals = newData.centerFieldGoals + oldData.centerFieldGoals,
            centerFieldGoalsMade = newData.centerFieldGoalsMade + oldData.centerFieldGoalsMade,
            leftSideFieldGoals = newData.leftSideFieldGoals + oldData.leftSideFieldGoals,
            leftSideFieldGoalsMade = newData.leftSideFieldGoalsMade + oldData.leftSideFieldGoalsMade,
            rightSideFieldGoals = newData.rightSideFieldGoals + oldData.rightSideFieldGoals,
            rightSideFieldGoalsMade = newData.rightSideFieldGoalsMade + oldData.rightSideFieldGoalsMade
        )
    }

    private fun convertToEntities(list: List<Exercise>): List<ExerciseEntity> {
        val entityList = mutableListOf<ExerciseEntity>()
        for (exercise in list) {
            entityList.add(exercise.mapToEntity())
        }
        return entityList
    }
}