package co.edu.cuc.onlinelibrary.auth.domain.dto.tree;

import java.util.ArrayList;
import java.util.List;

public interface NodeTree {
    Integer getId();

    Integer getParentId();

    List<? extends NodeTree> getChildren();
    void setChildren(List<? extends NodeTree> children);

    static <T extends NodeTree> List<T> buildNestedNodeTree(List<T> nodes) {
        List<T> roots = new ArrayList<>();

        for (T node : nodes) {
            if (node.getParentId() == null) {
                buildTreeRecursive(node, nodes);
                roots.add(node);
            }
        }

        return roots;
    }

    static <T extends NodeTree> void buildTreeRecursive(T parentNode, List<T> allNodes) {
        List<T> children = new ArrayList<>();

        for (T node : allNodes) {
            if (node.getParentId() != null && node.getParentId().equals(parentNode.getId())) {
                buildTreeRecursive(node, allNodes);
                children.add(node);
            }
        }

        parentNode.setChildren(children);
    }
}
