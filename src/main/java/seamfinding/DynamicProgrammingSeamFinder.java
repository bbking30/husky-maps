package seamfinding;

import seamfinding.energy.EnergyFunction;

import java.util.*;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {

    @Override
    public List<Integer> findHorizontal(Picture picture, EnergyFunction f) {
        // TODO: Replace with your code
        int width = picture.width();
        int height = picture.height();
        double[][] imagePathWeights = new double[height][width];
        int[][] backTrackTraversals = new int[height][width];

        for (int row = 0; row < height; row++) {
            imagePathWeights[row][0] = f.apply(picture, 0, row);
        }

        for (int column = 1; column < width; column++) {
            for (int row = 0; row < height; row++) {
                double minNeighborEnergy = imagePathWeights[row][column - 1];
                int minNeighborRow = row;

                if (row > 0 && imagePathWeights[row - 1][column - 1]
                        < minNeighborEnergy) {
                    minNeighborEnergy = imagePathWeights[row - 1][column - 1];
                    minNeighborRow = row - 1;
                }

                if (row < height - 1 && imagePathWeights[row + 1][column - 1]
                        < minNeighborEnergy) {
                    minNeighborEnergy = imagePathWeights[row + 1][column - 1];
                    minNeighborRow = row + 1;
                }

                imagePathWeights[row][column] = f.apply(picture, column, row)
                        + minNeighborEnergy;
                backTrackTraversals[row][column] = minNeighborRow;
            }
        }

        double minEnergy = imagePathWeights[0][width - 1];
        int currMinRow = 0;
        List<Integer> seam = new ArrayList<>();

        for (int column = width - 1; column >= 0; column--) {
            for (int row = 1; row < height; row++) {
                if (imagePathWeights[row][width - 1] < minEnergy) {
                    minEnergy = imagePathWeights[row][column];
                    currMinRow = row;
                }
            }

            seam.add(currMinRow);
            currMinRow = backTrackTraversals[currMinRow][column];
        }

        Collections.reverse(seam);
        return seam;

        //throw new UnsupportedOperationException("Not implemented yet");
    }
}


