package permutation;

/**
 *
 * @author kaitlyn--Junyi Li
 */
public class RunPermutation {

    private final PermutationModel model;
    private final PermutationView view;
    private final PermutationController controller;

    RunPermutation() {
        model = new PermutationModel();
        view = new PermutationView();
        controller = new PermutationController();

        controller.addModel(model);
        controller.addView(view);

        //add controller as listener
        view.addController(controller);
        //initialize view
        controller.initView();

    }

    public static void main(String[] args) {
        RunPermutation runPermutation = new RunPermutation();

    }

}
