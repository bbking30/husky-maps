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
        int[][] backtrack = new int[height][width];

        for (int row = 0; row < height; row++) {
            imagePathWeights[row][0] = f.apply(picture, 0, row);
        }

        for (int col = 1; col < width; col++) {
            for (int row = 0; row < height; row++) {
                double minNeighborEnergy = imagePathWeights[row][col - 1];
                int minNeighborRow = row;

                if (row > 0 && imagePathWeights[row - 1][col - 1] < minNeighborEnergy) {
                    minNeighborEnergy = imagePathWeights[row - 1][col - 1];
                    minNeighborRow = row - 1;
                }

                if (row < height - 1 && imagePathWeights[row + 1][col - 1] < minNeighborEnergy) {
                    minNeighborEnergy = imagePathWeights[row + 1][col - 1];
                    minNeighborRow = row + 1;
                }

                imagePathWeights[row][col] = f.apply(picture, col, row) + minNeighborEnergy;
                backtrack[row][col] = minNeighborRow;
            }
        }

        double minEnergy = imagePathWeights[0][width - 1];
        int minRow = 0;
        for (int row = 1; row < height; row++) {
            if (imagePathWeights[row][width - 1] < minEnergy) {
                minEnergy = imagePathWeights[row][width - 1];
                minRow = row;
            }
        }

        List<Integer> seam = new ArrayList<>();
        for (int col = width - 1; col >= 0; col--) {
            seam.add(minRow);
            minRow = backtrack[minRow][col];
        }

        Collections.reverse(seam);
        return seam;

        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
