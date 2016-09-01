/**
 */
package org.eclipse.papyrus.designer.transformation.profile.Transformation.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage;
import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Apply Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ApplyTransformationImpl#getTrafo <em>Trafo</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ApplyTransformationImpl#getBase_Element <em>Base Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ApplyTransformationImpl extends MinimalEObjectImpl.Container implements ApplyTransformation {
	/**
	 * The cached value of the '{@link #getTrafo() <em>Trafo</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrafo()
	 * @generated
	 * @ordered
	 */
	protected EList<M2MTrafo> trafo;

	/**
	 * The cached value of the '{@link #getBase_Element() <em>Base Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_Element()
	 * @generated
	 * @ordered
	 */
	protected Element base_Element;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplyTransformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransformationPackage.Literals.APPLY_TRANSFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<M2MTrafo> getTrafo() {
		if (trafo == null) {
			trafo = new EObjectResolvingEList<M2MTrafo>(M2MTrafo.class, this, TransformationPackage.APPLY_TRANSFORMATION__TRAFO);
		}
		return trafo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element getBase_Element() {
		if (base_Element != null && base_Element.eIsProxy()) {
			InternalEObject oldBase_Element = (InternalEObject)base_Element;
			base_Element = (Element)eResolveProxy(oldBase_Element);
			if (base_Element != oldBase_Element) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransformationPackage.APPLY_TRANSFORMATION__BASE_ELEMENT, oldBase_Element, base_Element));
			}
		}
		return base_Element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element basicGetBase_Element() {
		return base_Element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_Element(Element newBase_Element) {
		Element oldBase_Element = base_Element;
		base_Element = newBase_Element;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.APPLY_TRANSFORMATION__BASE_ELEMENT, oldBase_Element, base_Element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TransformationPackage.APPLY_TRANSFORMATION__TRAFO:
				return getTrafo();
			case TransformationPackage.APPLY_TRANSFORMATION__BASE_ELEMENT:
				if (resolve) return getBase_Element();
				return basicGetBase_Element();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TransformationPackage.APPLY_TRANSFORMATION__TRAFO:
				getTrafo().clear();
				getTrafo().addAll((Collection<? extends M2MTrafo>)newValue);
				return;
			case TransformationPackage.APPLY_TRANSFORMATION__BASE_ELEMENT:
				setBase_Element((Element)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TransformationPackage.APPLY_TRANSFORMATION__TRAFO:
				getTrafo().clear();
				return;
			case TransformationPackage.APPLY_TRANSFORMATION__BASE_ELEMENT:
				setBase_Element((Element)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TransformationPackage.APPLY_TRANSFORMATION__TRAFO:
				return trafo != null && !trafo.isEmpty();
			case TransformationPackage.APPLY_TRANSFORMATION__BASE_ELEMENT:
				return base_Element != null;
		}
		return super.eIsSet(featureID);
	}

} //ApplyTransformationImpl
